package com.qmms.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String date2str(String formate, Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(formate);
        return sf.format(date);
    }

    public static Date strToDate(String format, String dateStr) {
        SimpleDateFormat dd = new SimpleDateFormat(format);
        Date date = null;
        if (StringUtils.isNotEmpty(dateStr)) {
            try {
                date = dd.parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static boolean validDateFormatString(String format,String dateStr){
        boolean result = false;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if(cal.get(cal.YEAR) < 1970) {
                result = false;
            } else {
                result = true;
            }
        } catch (ParseException e) {
        }
        return result;
    }
}
