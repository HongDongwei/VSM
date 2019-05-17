package com.codvision.vsm.ui.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.DeleteSchedule;
import com.codvision.vsm.module.bean.GetSchedule;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.presenter.DeleteSchedulePresenter;
import com.codvision.vsm.presenter.GetSchedulePresenter;
import com.codvision.vsm.presenter.contract.DeleteScheduleContract;
import com.codvision.vsm.presenter.contract.GetScheduleContract;
import com.codvision.vsm.ui.activity.AddScheduleActivity;
import com.codvision.vsm.ui.activity.CalendarActivity;
import com.codvision.vsm.ui.adapter.TodayScheduleAdapter;
import com.codvision.vsm.utils.DayUtils;
import com.codvision.vsm.utils.FindCommand;
import com.codvision.vsm.utils.JsonParser;
import com.codvision.vsm.utils.SharedPreferenceUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.codvision.vsm.utils.DayUtils.isDay;

public class TodayFragment extends Fragment implements View.OnClickListener, GetScheduleContract.View, DeleteScheduleContract.View {

    /**
     * TAG
     */
    public static final String TAG = "TodayFragment";

    private View view;
    private TextView tvTextLink;
    private ImageButton ibAdd;
    private FindCommand findCommand;
    private ImageView ivCalendar;

    private TextView tvDay;
    private TextView tvWeek;
    private TextView tvBack;

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private boolean speaker;

    private MaterialCalendarView materialCalendarView;//布局内的控件
    private CalendarDay currentDate;//自定义的日期对象
    private Calendar todayDate;
    private ListView lvSchedule;
    private List<Schedule> scheduleArrayList = new ArrayList<Schedule>();
    private TodayScheduleAdapter todayScheduleAdapter;

    private GetSchedulePresenter getSchedulePresenter;
    private DeleteSchedulePresenter deleteSchedulePresenter;
    private GetSchedule getSchedule;
    private int year;
    private int month;
    private int day;
    private String time;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, container, false);
        requestRecordAudioPermission();
        initView();
        initEvent();
        initSpeech();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        materialCalendarView.setSelectedDate(new Date());
        initToady(true, CalendarDay.from(new Date()));
        super.onResume();
    }


    private void initView() {
        // 实例化
        ibAdd = (ImageButton) view.findViewById(R.id.ib_add);
        ivCalendar = (ImageView) view.findViewById(R.id.iv_calendar);
        tvDay = (TextView) view.findViewById(R.id.tv_today_day);
        tvWeek = (TextView) view.findViewById(R.id.tv_today_week);
        tvBack = (TextView) view.findViewById(R.id.tv_back);
        todayScheduleAdapter = new TodayScheduleAdapter(getActivity(), R.layout.item_schedule, scheduleArrayList);
        lvSchedule = view.findViewById(R.id.lv_schedule);
        getSchedulePresenter = new GetSchedulePresenter(this, getActivity());
        deleteSchedulePresenter = new DeleteSchedulePresenter(this, getActivity());
        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        findCommand = new FindCommand(getActivity());
        todayDate = Calendar.getInstance();
    }

    private void initEvent() {
        ibAdd.setOnClickListener(this);
        ivCalendar.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        lvSchedule.setAdapter(todayScheduleAdapter);
        ibAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startSpeechDialog();
                return true;
            }
        });
        lvSchedule.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showNormalDialog(position);
                return false;
            }
        });
    }

    private void showNormalDialog(final int position) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setTitle("温馨提示：");
        normalDialog.setMessage("请问是否删除该进程?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        deleteSchedulePresenter.deleteSchedule(new DeleteSchedule(scheduleArrayList.get(position).getId()));
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_calendar: //语音识别（把声音转文字）
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_add: //语音识别（把声音转文字）
                intent = new Intent(getActivity(), AddScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_back: //语音识别（把声音转文字）
                materialCalendarView.setSelectedDate(new Date());
                initToady(true, CalendarDay.from(new Date()));
                break;
            default:
                break;
        }
    }

    private void initToady(Boolean today, CalendarDay currentDate) {
        year = currentDate.getYear();
        month = currentDate.getMonth() + 1; //月份跟系统一样是从0开始的，实际获取时要加1
        day = currentDate.getDay();
        time = year + "-";
        if (month < 10) {
            time += "0" + month;
        } else {
            time += month;
        }

        if (day < 10) {
            time += "-" + "0" + day;
        } else {
            time += "-" + day;
        }
        if (today) {
            tvWeek.setVisibility(View.GONE);
            tvBack.setVisibility(View.GONE);
            tvDay.setText("今日日程");

        } else {
            tvWeek.setVisibility(View.VISIBLE);
            tvBack.setVisibility(View.VISIBLE);
            tvDay.setText(time);
            tvWeek.setText("周" + DayUtils.getWeek(year, month, day));
        }
        materialCalendarView.state().edit().commit();
        getSchedule = new GetSchedule(time, SharedPreferenceUtils.getUserId(getActivity()));
        Log.i(TAG, "initToady: getSchedule" + getSchedule.getTime());
        getSchedulePresenter.getSchedule(getSchedule);
    }

    private void initData() {

        // 显示兴起补全的整个礼拜的上个月或者下个月的日期 一般会多出一行整个礼拜
        // 点击补全出来的另外一个月的信息 可以直接跳到那个月
        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        // 设置日历默认的时间为当前的时间
        materialCalendarView.setSelectedDate(new Date());
        initToady(true, CalendarDay.from(new Date()));
        //编辑日历属性
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.WEEKS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        //      设置点击日期的监听
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDate = date;
                if (CalendarDay.from(new Date()).equals(currentDate)) {
                    initToady(true, date);
                } else {
                    initToady(false, date);
                }

            }
        });

    }

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility.createUtility(getActivity(), SpeechConstant.APPID + "=5cc3450e");
    }

    private void speekText(String s) {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(getActivity(), null);
//2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
//设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); // 设置发音人
        mTts.setParameter(SpeechConstant.ASR_PTT, "0");
        mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
//设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
//保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
//仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
//3.开始合成
        mTts.startSpeaking(s, new MySynthesizerListener());

    }


    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
        }

        @Override
        public void onSpeakPaused() {
        }

        @Override
        public void onSpeakResumed() {
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        @Override
        public void onCompleted(SpeechError error) {

        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }

    private void requestRecordAudioPermission() {
        //check API version, do nothing if API version < 23!
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion > android.os.Build.VERSION_CODES.LOLLIPOP) {

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.RECORD_AUDIO)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d("Activity", "Granted!");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d("Activity", "Denied!");
                    getActivity().finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void startSpeechDialog() {
//        if (mywakeuper.isListening()) {
//            mywakeuper.stopListening();
//        }
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(getActivity(), new MyInitListener());
        //2. 设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");// 设置中文
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(new MyRecognizerDialogListener());
        //4. 显示dialog，接收语音输入
        mDialog.show();
        tvTextLink = (TextView) mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        tvTextLink.setText("小莫正在为您服务^_^！！！");
        tvTextLink.getPaint().setFlags(Paint.SUBPIXEL_TEXT_FLAG);//取消下划线
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        /**
         * @param results
         * @param isLast  是否说完了
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
            System.out.println(" 没有解析的 :" + result);

            String text = JsonParser.parseIatResult(result);//解析过后的
            System.out.println(" 解析后的 :" + text);

            String sn = null;
            // 读取json结果中的 sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString());
                sn = resultJson.optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults.put(sn, text);//没有得到一句，添加到

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults.get(key));
            }
            if (isLast) {
                // speekText(findCommand.findCommand(resultBuffer.toString()));
                Intent intent = new Intent();
                intent.putExtra("goodId", resultBuffer.toString());//设置参数,""
                intent.setClass(getActivity(), AddScheduleActivity.class);//从哪里跳到哪里
                getActivity().startActivity(intent);
            }
            speaker = false;
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    /**
     * 语音识别
     */
    private void startSpeech() {
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(getActivity(), null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");// 设置中文
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");// 设置普通话
        //3. 开始听写
        mIat.startListening(mRecoListener);
    }


    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
//一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
//关于解析Json的代码可参见 Demo中JsonParser 类；
//isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.e(TAG, results.getResultString());
            System.out.println(results.getResultString());
            showTip(results.getResultString());
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));
            // 获取错误码描述
            Log.e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
        }
    };

    private void showTip(String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getScheduleSuccess(ArrayList<Schedule> arrayList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Log.i(TAG, "getScheduleSuccess: 成功了" + arrayList.size() + " day:" + df.format(arrayList.get(0).getStartdate()));
        scheduleArrayList.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            Schedule schedule = arrayList.get(i);
            if (schedule.getCycle().equals("0")) {
                scheduleArrayList.add(schedule);
            } else {
                if (isDay(Integer.parseInt(schedule.getCycle()), df.format(schedule.getStartdate()) + "", df.format(schedule.getEnddate()) + "", time)) {
                    Log.i(TAG, "getScheduleSuccess: true");
                    scheduleArrayList.add(schedule);
                }
            }

        }
        todayScheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void getScheduleFail(String code, String message) {
        Log.i(TAG, "loginFail: " + message);
    }

    @Override
    public void deleteScheduleSuccess(ArrayList<Schedule> arrayList) {
        getSchedule = new GetSchedule(time, SharedPreferenceUtils.getUserId(getActivity()));
        Log.i(TAG, "initToady: getSchedule" + getSchedule.getTime());
        getSchedulePresenter.getSchedule(getSchedule);
    }

    @Override
    public void deleteScheduleFail(String code, String message) {
        Log.i(TAG, "deleteScheduleFail: message:" + message);
    }
}
