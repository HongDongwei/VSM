package com.codvision.vsm.utils;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class FindCommand {
    private Context context;
    private String content;
    private int position;
    private int type = 0;
    private int year, month, day, hour, minute;
    private Calendar calendar;

    public int getYear() {
        return year;
    }

    public String getMonth() {
        if (month < 10) {
            return "0" + month;
        }
        return month + "";
    }

    public String getDay() {
        if (day < 10) {
            return "0" + day;
        }
        return day + "";
    }

    public String getHour() {
        if (hour < 10) {
            return "0" + hour;
        }
        return hour + "";
    }

    public String getMinute() {
        if (minute < 10) {
            return "0" + minute;
        }
        return minute + "";
    }

    public FindCommand(Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
        initGetTime();


    }

    private void initGetTime() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    public void setContent(String content) {
        this.content = content;
        calendar = Calendar.getInstance();
        getTime();
    }

    private void getTime() {
        if (content.contains("大后天")) {
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            initGetTime();
        } else if (content.contains("后天")) {
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            initGetTime();
        } else if (content.contains("明天")) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            initGetTime();
        }


        if (content.contains("月")) {
            position = getPosition("月", content);
            if (position > 1) {
                if (isNum(content.charAt(position - 2) + "") && isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 2) + "" + content.charAt(position - 1) + "");
                    if (a < 13) {
                        month = a;
                    }
                } else {
                    if (isNum(content.charAt(position - 1) + "")) {
                        int a = Integer.parseInt(content.charAt(position - 1) + "");
                        month = a;
                    }
                }
            }
            if (position == 1) {
                if (isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 1) + "");
                    month = a;
                }
            }
        }

        if (content.contains("号")) {
            position = getPosition("号", content);
            if (position > 1) {
                if (isNum(content.charAt(position - 2) + "") && isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 2) + "" + content.charAt(position - 1) + "");
                    if (a < 32) {
                        day = a;
                    }
                } else {
                    if (isNum(content.charAt(position - 1) + "")) {
                        int a = Integer.parseInt(content.charAt(position - 1) + "");
                        day = a;
                    }
                }
            }
            if (position == 1) {
                if (isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 1) + "");
                    month = a;
                }
            }
        }
        if (content.contains(":")) {
            position = getPosition(":", content);
            if (position > 1) {
                if (isNum(content.charAt(position - 2) + "") && isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 2) + "" + content.charAt(position - 1) + "");
                    if (a < 25) {
                        hour = a;
                    }
                } else {
                    if (isNum(content.charAt(position - 1) + "")) {
                        int a = Integer.parseInt(content.charAt(position - 1) + "");
                        hour = a;
                    }

                }
            }
            if (position == 1) {
                if (isNum(content.charAt(position - 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position - 1) + "");
                    hour = a;
                }
            }
            if (content.length() > position + 2) {
                if (isNum(content.charAt(position + 2) + "") && isNum(content.charAt(position + 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position + 1) + "" + content.charAt(position + 2) + "");
                    if (a < 61) {
                        minute = a;
                    }
                }
            }
            if (content.length() == position + 2) {
                if (isNum(content.charAt(position + 1) + "")) {
                    int a = Integer.parseInt(content.charAt(position + 1) + "");
                    if (a < 60) {
                        minute = a;
                    }
                }
            }
        }

    }

    private int getPosition(String s, String content) {
        for (int i = 0; i < content.length(); i++) {
            //判断当前字符串中的单个位置
            if (s.equals(String.valueOf(content.charAt(i)))) {
                return i;
            }
        }
        return 0;
    }

    private String getString(int a, int b, String s) {
        return s.substring(a, b);
    }

    private boolean isNum(String s) {
        if (s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9")) {
            return true;
        }
        return false;
    }
}
