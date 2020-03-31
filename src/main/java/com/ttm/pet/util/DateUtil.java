package com.ttm.pet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date String2Date(String DateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(DateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String Date2String(Date date) {
        String newDate;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        newDate = sdf.format(date);
        return newDate;
    }

    /**
     * 字符串转秒
     * @param DateStr
     * @return
     */
    public static Long string2long(String DateStr) {
        Long timestamp = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timestamp = sdf.parse(DateStr).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
    /**
     * 秒数转时间
     * @param timestamp
     * @return
     */
    public static Date long2date(long timestamp) {
        Date date = new Date();
        long time = timestamp * 1000l;
        date.setTime(time);
        return date;
    }

    /**
     * 字符串转秒
     * @param date
     * @return
     */
    public static Long date2long(Date date) {
        Long timestamp = null;
        timestamp = date.getTime()/1000;
        return timestamp;
    }
}
