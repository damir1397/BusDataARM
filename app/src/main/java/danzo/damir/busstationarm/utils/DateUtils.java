package danzo.damir.busstationarm.utils;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String format1 = "ddMMMyy H:mm:ss";
    public static final String mySqlFormat = "yyyy-MM-dd HH:mm:ss";

    public static String time2formatString(String time) {
        if (TextUtils.isEmpty(time)) {
            return time;
        }

        try {
            return new SimpleDateFormat(
                    format1,
                    Locale.getDefault()).format(TimeUtils.string2Date(time));

        } catch (Exception ignored) {
        }
        return time;
    }

    public static String addDaysToCurrentDate(final String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, days);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date(cal.getTimeInMillis()));
    }

    public static Date addSecondsToCurrentDate(int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, seconds);
        return new Date(cal.getTimeInMillis());
    }

    public static String addSecondsToCurrentTimeToString(final String dateFormat, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date(cal.getTimeInMillis()));
    }

    public static String addDaysToDateFormat(final String date, final String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        try {
            return s.format(new Date(s.parse(date).getTime()));
        } catch (ParseException e) {
            Log.e("TAG", "Error in Parsing Date : " + e.getMessage());
        }
        return null;
    }

    public static String addMinutesToCurrentTimeToString(final String dateFormat, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, minutes);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date(cal.getTimeInMillis()));
    }

    public static String addMinutesInTimePlusMinutes(final String dateFormat, String time, String minutes, int minutes2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date d = simpleDateFormat.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, (Integer.parseInt(minutes) + minutes2));
        return simpleDateFormat.format(cal.getTimeInMillis());
    }

}
