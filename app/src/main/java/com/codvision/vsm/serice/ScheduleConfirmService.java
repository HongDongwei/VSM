package com.codvision.vsm.serice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.codvision.vsm.base.Constant;
import com.codvision.vsm.utils.DayUtils;

import org.apache.mina.core.buffer.IoBuffer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleConfirmService extends Service {
    /**
     * TAG
     */
    public static final String TAG = "ScheduleConfirmService";

    private Timer timer = new Timer();
    private TimerTask task;
    private Calendar calendar;
    private SocketBinder sockerBinder = new SocketBinder();
    private Callback callback;

    @Override
    public IBinder onBind(Intent intent) {
        startConfirm();
        return sockerBinder;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public class SocketBinder extends Binder {

        /*返回SocketService 在需要的地方可以通过ServiceConnection获取到SocketService  */
        public ScheduleConfirmService getService() {
            return ScheduleConfirmService.this;
        }
    }

    public static interface Callback {
        void onDataChange(String data, int id);
    }

    //定时发送数据
    private void startConfirm() {

        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                calendar = Calendar.getInstance();
                                for (int i = 0; i < Constant.scheduleArrayList.size(); i++) {
                                    if (DayUtils.compareTime(calendar, Constant.scheduleArrayList.get(i).getPlace()) && (Constant.scheduleArrayList.get(i).getState() == 0)) {
                                        if (callback != null) {
                                            callback.onDataChange(Constant.scheduleArrayList.get(i).getThing(), Constant.scheduleArrayList.get(i).getId());
                                        }
                                        break;
                                    }
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        //   Log.i(TAG, "run: " + calendar.toString());

                    } catch (
                            Exception e) {
                    }
                }
            };
        }
        timer.schedule(task, 0, 10000);
    }

    //关闭定时发送数据
    private void stopTimer() {

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
