package com.codvision.vsm.utils.datepicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtils {

    private static final String DATE_FORMAT_PATTERN_YMD = "yyyy-MM";
    private static final String DATE_FORMAT_PATTERN_YMD_HM = "yyyy-M-d H:m";
    private static final String DATE_FORMAT_YMD = "yyyy年MM月";
    private static final String DATE_FORMAT_YMD_HM = "yyyy年M月d日 H点m分";

    /**
     * 时间戳转字符串
     *
     * @param timestamp     时间戳
     * @param isPreciseTime 是否包含时分
     * @return 格式化的日期字符串
     */
    public static String long2Str(long timestamp, boolean isPreciseTime) {
        return long2Str(timestamp, getFormatPattern(isPreciseTime));
    }

    public static String longStr(long timestamp, boolean isPreciseTime) {
        return long2Str(timestamp, getFormat (isPreciseTime));
    }

    private static String long2Str(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(new Date(timestamp));
    }

    /**
     * 字符串转时间戳
     *
     * @param dateStr       日期字符串
     * @param isPreciseTime 是否包含时分
     * @return 时间戳
     */
    public static long str2Long(String dateStr, boolean isPreciseTime) {
        return str2Long(dateStr, getFormatPattern(isPreciseTime));
    }

    private static long str2Long(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.CHINA).parse(dateStr).getTime();
        } catch (Throwable ignored) {
        }
        return 0;
    }

    private static String getFormatPattern(boolean showSpecificTime) {
        if (showSpecificTime) {
            return DATE_FORMAT_PATTERN_YMD_HM;
        } else {
            return DATE_FORMAT_PATTERN_YMD;
        }
    }

    private static String getFormat(boolean showSpecificTime) {
        if (showSpecificTime) {
            return DATE_FORMAT_YMD_HM;
        } else {
            return DATE_FORMAT_YMD;
        }
    }
}
