package com.codvision.vsm.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectUtils {

    private static final String TAG = "HeartBeatService";

    public static final int REPEAT_TIME = 5;//��ʾ��������
    public static final String HOST = "192.168.1.104";//��ʾIP��ַ
    public static final int PORT = 60000;//��ʾ�˿ں�
    public static final int TIMEOUT = 5;//�������ӳ�ʱʱ��,����5s��û�����ϱ��׳��쳣

    public static final int LOGIN = 1;
    public static final int UNLOGIN = 0;
    public static final int LOGINOUT = -1;
    public static final int LOGINWRONG = -2;


    /**
     * ��ȡ��ǰʱ��
     *
     * @return
     */
    public static String stringNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }



}
