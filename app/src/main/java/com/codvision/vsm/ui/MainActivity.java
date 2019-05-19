package com.codvision.vsm.ui;

import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.module.bean.ScheduleSubmit;
import com.codvision.vsm.presenter.ScheduleSubmitPresenter;
import com.codvision.vsm.presenter.contract.ScheduleSubmitContract;
import com.codvision.vsm.serice.ScheduleConfirmService;
import com.codvision.vsm.ui.fragment.PlanFragment;
import com.codvision.vsm.ui.fragment.TodayFragment;
import com.codvision.vsm.ui.fragment.UserFragment;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import static com.codvision.vsm.utils.DayUtils.getTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ScheduleSubmitContract.View {
    /**
     * TAG
     */
    public static final String TAG = "MainActivity";

    private TextView tvToday;
    private TextView tvPlan;
    private TextView tvUser;

    private ScheduleSubmitPresenter presenter;
    private PlanFragment planFragment;
    private TodayFragment todayFragment;
    private UserFragment userFragment;

    private FragmentManager fragmentManager;
    private boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intiEvent();
        initSpeech();
    }

    private void initView() {
        tvToday = (TextView) findViewById(R.id.tv_main_today);
        tvPlan = (TextView) findViewById(R.id.tv_main_plan);
        tvUser = (TextView) findViewById(R.id.tv_main_user);
        planFragment = new PlanFragment();
        todayFragment = new TodayFragment();
        userFragment = new UserFragment();
        fragmentManager = getFragmentManager();
        presenter = new ScheduleSubmitPresenter(this, this);
        doBindService();
    }

    private void intiEvent() {
        tvToday.setClickable(true);
        tvPlan.setClickable(true);
        tvUser.setClickable(true);

        tvToday.setOnClickListener(this);
        tvPlan.setOnClickListener(this);
        tvUser.setOnClickListener(this);


        //首页
        fragmentManager.beginTransaction().replace(R.id.main_fragment, todayFragment).commit();
        setClick(tvToday);
    }

    private void setClick(TextView textView) {
        tvToday.setSelected(false);
        tvPlan.setSelected(false);
        tvUser.setSelected(false);

        Drawable iconUser = getResources().getDrawable(R.drawable.user_unclick);
        iconUser.setBounds(0, 0, 80, 80);
        tvUser.setCompoundDrawables(null, iconUser, null, null);
        Drawable iconOrder = getResources().getDrawable(R.drawable.plan_unclick);
        iconOrder.setBounds(0, 0, 80, 80);
        tvPlan.setCompoundDrawables(null, iconOrder, null, null);
        Drawable iconHome = getResources().getDrawable(R.drawable.home_unclick);
        iconHome.setBounds(0, 0, 80, 80);
        tvToday.setCompoundDrawables(null, iconHome, null, null);

        switch (textView.getId()) {
            case R.id.tv_main_today:
                iconHome = getResources().getDrawable(R.drawable.home_click);
                iconHome.setBounds(0, 0, 80, 80);
                tvToday.setCompoundDrawables(null, iconHome, null, null);

                break;
            case R.id.tv_main_plan:
                iconOrder = getResources().getDrawable(R.drawable.plan_click);
                iconOrder.setBounds(0, 0, 80, 80);
                tvPlan.setCompoundDrawables(null, iconOrder, null, null);
                break;
            case R.id.tv_main_user:
                iconUser = getResources().getDrawable(R.drawable.user_click);
                iconUser.setBounds(0, 0, 80, 80);
                tvUser.setCompoundDrawables(null, iconUser, null, null);
                break;
        }
        textView.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_today:
                setClick(tvToday);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, todayFragment).commit();
                break;
            case R.id.tv_main_plan:
                setClick(tvPlan);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, planFragment).commit();
                break;
            case R.id.tv_main_user:
                setClick(tvUser);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, userFragment).commit();
                break;
            default:
                break;
        }
    }

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5cc3450e");
    }

    private void speekText(String s) {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(this, null);
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

    @Override
    public void submitScheduleSuccess() {

    }

    @Override
    public void submitScheduleFail(String code, String message) {

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

    private ScheduleConfirmService scheduleConfirmService;


    private ServiceConnection mConnection = new ServiceConnection() {


        public void onServiceConnected(ComponentName className, IBinder service) {

            scheduleConfirmService = ((ScheduleConfirmService.SocketBinder) service).getService();

            //回调监听
            scheduleConfirmService.setCallback(new ScheduleConfirmService.Callback() {

                @Override
                public void onDataChange(String data, int id) {
                    for (int i = 0; i < Constant.scheduleArrayList.size(); i++) {
                        if (Constant.scheduleArrayList.get(i).getId() == id) {
                            Constant.scheduleArrayList.get(i).setState(1);
                        }
                    }
                    Message msg = new Message();
                    msg.obj = data;
                    msg.what = id;
                    handler.sendMessage(msg);
                }
            });
        }

        public void onServiceDisconnected(ComponentName className) {
            scheduleConfirmService = null;
        }
    };

    /**
     * 绑定服务
     */
    void doBindService() {
        bindService(new Intent(MainActivity.this, ScheduleConfirmService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    /**
     * 解除绑定
     */
    void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < Constant.scheduleArrayList.size(); i++) {
                if (Constant.scheduleArrayList.get(i).getId() == msg.what) {
                    presenter.submitSchedule(new ScheduleSubmit(Constant.scheduleArrayList.get(i).getId(), 1));
                }
            }
            speekText("您有一个" + msg.obj.toString() + "的任务还没完成，请尽快完成");
            Log.i(TAG, "handleMessage: " + msg.obj.toString());
        }
    };
}
