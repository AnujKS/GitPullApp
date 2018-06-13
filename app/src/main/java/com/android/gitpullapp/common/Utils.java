package com.android.gitpullapp.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String changeDateFormat(String dateString) {
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMM yyyy");
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            return "-";
        }
        return newDateFormat.format(date);
    }
}
