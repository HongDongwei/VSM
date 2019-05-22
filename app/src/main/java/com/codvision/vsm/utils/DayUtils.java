package com.codvision.vsm.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayUtils {

    /**
     * TAG
     */
    public static final String TAG = "DayUtils";

    public static String getWeek(int year, int month, int day) {
        String s = "";
        Calendar c = Calendar.getInstance(); //获得当前年月日
        c.set(year, month - 1, day);    //获得星期,月份是从0-11月，所以要减1
        int x = c.get(Calendar.DAY_OF_WEEK) - 1;    //星期是从周日开始，减1就是星期x，看着舒服点
        switch (x) {
            case 0:
                s = "日";
                break;
            case 1:
                s = "一";
                break;
            case 2:
                s = "二";
                break;
            case 3:
                s = "三";
                break;
            case 4:
                s = "四";
                break;
            case 5:
                s = "五";
                break;
            case 6:
                s = "六";
                break;
            default:
                break;

        }
        return s;
    }

    public static String getTime(Calendar calendar) {
        return calendar.get(Calendar.HOUR) + "点" + calendar.get(Calendar.MINUTE) + "分";
    }

    public static boolean isDay(int interval, String fristDate, String endData, String checkDate) {
        Log.i(TAG, "isDay: interval=" + interval + " fristDate=" + fristDate + "checkDate=" + checkDate);
        Date fring = null, check = null, end = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fring = df.parse(fristDate);
            check = df.parse(checkDate);
            end = df.parse(endData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; check.compareTo(fring) >= 0 && end.compareTo(fring) >= 0; i += interval) {
            Log.i(TAG, "isDay: i=" + i + " fristDate=" + df.format(fring.getTime()) + "checkDate=" + checkDate);
            if (check.compareTo(fring) == 0) {
                return true;
            }
            fring = new Date(fring.getTime() + interval * 24 * 60 * 60 * 1000);
        }
        return false;
    }

    public static boolean isMounth(String fristDate, String endData, String checkDate) {
        Log.i(TAG, "isDay: fristDate=" + fristDate + " endData=" + endData + "checkDate=" + checkDate);
        if (getYear(fristDate) <= getYear(checkDate) && getYear(checkDate) <= getYear(endData)) {
            if (getMounth(fristDate) <= getMounth(checkDate) && getMounth(checkDate) <= getMounth(endData)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDay(String fristDate, String endData, String checkDate) {
        Log.i(TAG, "isDay: fristDate=" + fristDate + " endData=" + endData + "checkDate=" + checkDate);
        if (getD(endData) < getD(checkDate)) {
            return true;
        }
        return false;
    }

    public static int getYear(String time) {
        String year = time.charAt(0) + "" + time.charAt(1) + time.charAt(2) + time.charAt(3);
        return Integer.parseInt(year);
    }

    public static int getMounth(String time) {
        String moubth = time.charAt(5) + "" + time.charAt(6);
        return Integer.parseInt(moubth);
    }

    public static int getD(String time) {
        String moubth = time.charAt(8) + "" + time.charAt(9);
        return Integer.parseInt(moubth);
    }

    public static String getDay(String time) {

        return time.charAt(0) + "" + time.charAt(1) + time.charAt(2) + time.charAt(3) + time.charAt(4) + time.charAt(5) + time.charAt(6) + time.charAt(7) + time.charAt(8) + time.charAt(9);

    }

    public static String getTime(String time) {
        return time.charAt(11) + "" + time.charAt(12) + time.charAt(13) + time.charAt(14) + time.charAt(15);

    }

    public static boolean compareTime(Calendar calendar, String time) {
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int nowMinute = calendar.get(Calendar.MINUTE);
        String strHour = time.charAt(0) + "" + time.charAt(1);
        String strMinute = time.charAt(3) + "" + time.charAt(4);
        int hour = Integer.parseInt(strHour);
        int minute = Integer.parseInt(strMinute);
        if (nowHour > hour || (nowHour == hour && nowMinute >= minute)) {
            return true;
        }
        return false;
    }

//        public static int dayDecline(String preTime,String nowTime){
//            int preDay=Integer.parseInt(preTime.charAt(8) + "" + preTime.charAt(9));
//            int nowDay=Integer.parseInt(nowTime.charAt(8) + "" + nowTime.charAt(9));
//            int preMounth=Integer.parseInt(preTime.charAt(5) + "" + preTime.charAt(6));
//            int nowMounth=Integer.parseInt(nowTime.charAt(5) + "" + nowTime.charAt(6));
//            int preYear=Integer.parseInt(preTime.charAt(0) + "" + preTime.charAt(1) + preTime.charAt(2) + preTime.charAt(3));
//            int nowYear=Integer.parseInt(nowTime.charAt(0) + "" + nowTime.charAt(1) + nowTime.charAt(2) + nowTime.charAt(3));
//            if (nowDay<preDay)
//            return
//        }
}
