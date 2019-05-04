package com.codvision.vsm.utils;

import java.util.Calendar;

public class DayUtils {
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
}
