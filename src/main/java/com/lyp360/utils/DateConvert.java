package com.lyp360.utils;

/**
 * Created by junhao on 2016/5/2.
 * DateConvert
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter {
    private static String dateFormatStr = "yyyy/MM/dd";
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateFormatStr);

    private static String dateLongFormatStr = dateFormatStr+" HH:mm:ss";
    private static SimpleDateFormat dateTimeLongFormat = new SimpleDateFormat(dateLongFormatStr);

    public Object convert(Class arg0, Object arg1) {
        if(arg1 != null){
            System.out.println(arg1.getClass().getName()+"="+arg1.toString());
            String className = arg1.getClass().getName();
            //java.sql.Timestamp
            if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                    return df.parse(dateTimeLongFormat.format(arg1));
                } catch (Exception e) {
                    try {
                        SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
                        return df.parse(dateTimeFormat.format(arg1));
                    } catch (ParseException ex) {
                        e.printStackTrace();
                        return null;
                    }
                }
            } else if(arg1 instanceof  Long) {
                return new Date((long)arg1);
            } else if(arg1 instanceof  Date) {
                return (Date)arg1;
            } else {//java.util.Date,java.sql.Date
                String p = (String) arg1;
                if (p == null || p.trim().length() == 0) {
                    return null;
                }
                try {
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                    return df.parse(p.trim());
                } catch (Exception e) {
                    try {
                        SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
                        return df.parse(p.trim());
                    } catch (ParseException ex) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        } else {
            return "";
        }
    }

    public static String formatDateTime(Object obj) {
        if (obj != null)
            return dateTimeFormat.format(obj);
        else
            return "";
    }

    public static String formatLongDateTime(Object obj) {
        if (obj != null)
            return dateTimeLongFormat.format(obj);
        else
            return "";
    }

}