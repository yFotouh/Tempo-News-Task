package com.task.temponewstask.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {
    public static final String getTodayTimeString() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(c.getTime());
    }
    public static final String reformatBackEndDate(String backendDate) {
//        2020-08-14T10:36:39Z
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date newDate = null;
        try {
            newDate = format.parse(backendDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("hh:mm MMM dd,yyyy");
       return format.format(newDate);
    }
}
