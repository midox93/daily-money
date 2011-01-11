package com.bottleworks.commons.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarHelper {

    int firstDayOfWeek = 1;//1 SUN, 2 MON
    
    TimeZone timeZone;

    public CalendarHelper() {
    }

    public int getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Calendar calendar(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(firstDayOfWeek);
        if(timeZone!=null){
            cal.setTimeZone(timeZone);
        }
        cal.setTime(d);
        return cal;
    }

    public Date tomorrow(Date d) {
        Calendar cal = calendar(d);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public Date dateAfter(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    public Date dateBefore(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.DATE, -i);
        return cal.getTime();
    }

    public Date yearAfter(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }

    public Date yearBefore(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.YEAR, -i);
        return cal.getTime();
    }

    public Date monthAfter(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }

    public Date monthBefore(Date d, int i) {
        Calendar cal = calendar(d);
        cal.add(Calendar.MONTH, -i);
        return cal.getTime();
    }

    public Date today() {
        return new Date();
    }

    public Date yesterday(Date d) {
        Calendar cal = calendar(d);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public Date toDayStart(Date d) {
        return toDayStart(calendar(d));
    }
    
    public Date toDayMiddle(Date d) {
        return toDayMiddle(calendar(d));
    }

    public Date toDayEnd(Date d) {
        return toDayEnd(calendar(d));
    }
    
    public Date toDayStart(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public Date toDayMiddle(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public Date toDayEnd(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
    
    public int weekOfMonth(Date d) {
        Calendar cal = calendar(d);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    public int weekOfYear(Date d) {
        Calendar cal = calendar(d);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public Date monthStartDate(Date d) {
        Calendar cal = calendar(d);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return toDayStart(cal);
    }

    public Date monthEndDate(Date d) {
        Calendar cal = calendar(d);
        int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return toDayEnd(cal);
    }

    public Date yearStartDate(Date d) {
        Calendar cal = calendar(d);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return toDayStart(cal);
    }

    public Date yearEndDate(Date d) {
        Calendar cal = calendar(d);
        int last = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, last);
        return toDayEnd(cal);
    }

    public Date weekStartDate(Date d) {
        Calendar cal = calendar(d);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        return toDayStart(cal);
    }

    public Date weekEndDate(Date d) {
        Calendar cal = calendar(d);
//        int last = cal.getActualMaximum(Calendar.DAY_OF_WEEK);
        int last = (firstDayOfWeek==1)?7:firstDayOfWeek-1;
        cal.set(Calendar.DAY_OF_WEEK, last);
        return toDayEnd(cal);
    }
    
    
    
    static SimpleDateFormat RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'",
            new DateFormatSymbols(Locale.ENGLISH));
    static public final TimeZone UTC0 = TimeZone.getTimeZone("UTC0");
    static public final TimeZone GMT0 = TimeZone.getTimeZone("GMT+0:00");
    
    public static String getRFC1123(Date date) {
        return RFC1123.format(date);
    }

    public static Date parseRFC1123(String str) throws Exception {
        return RFC1123.parse(str);
}
}