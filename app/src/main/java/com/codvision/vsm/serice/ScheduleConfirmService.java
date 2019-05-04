package com.codvision.vsm.serice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

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
    private Calendar a = Calendar.getInstance();
    private Callback callback;

    @Override
    public IBinder onBind(Intent intent) {
        startConfirm();
        calendar = Calendar.getInstance();
        a.set(2019, 05, 3, 16, 47, 0);
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
        void onDataChange(String data);
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
                                if (calendar.get(Calendar.HOUR) > a.get(Calendar.HOUR) || (calendar.get(Calendar.HOUR) == a.get(Calendar.HOUR) && calendar.get(Calendar.MINUTE) > a.get(Calendar.MINUTE))) {
                                    if (callback != null) {
                                        callback.onDataChange("1");
                                    }
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                        Log.i(TAG, "run: " + calendar.toString());

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
