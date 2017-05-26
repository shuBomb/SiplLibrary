package com.androidutils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateOperations {
    static Calendar calender;

    public static String getCurrentDate(String dateFormat) {
        calender = Calendar.getInstance();

        return new SimpleDateFormat(dateFormat).format(calender.getTime());
    }

    public static String getCurrentDateGMT(String dateFormate) {
        calender = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(dateFormate);
        df.setTimeZone(TimeZone.getTimeZone("gmt"));
        return df.format(calender.getTime());
    }

    public static String DateFormatConverter(String date, String fromFormat, String toFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(toFormat);
        String newFormat = formatter.format(testDate);
        System.out.println(".....Date..." + newFormat);
        return newFormat;
    }

    public static String getDay(String sDate, String fromFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, Locale.getDefault());
        Date date;
        String dayOfTheWeek;
        try {
            date = dateFormat.parse(sDate);
            dayOfTheWeek = (String) android.text.format.DateFormat.format("dd", date);
        } catch (ParseException e) {
            return "";
        }
        return dayOfTheWeek;
    }

    public static String getMonth(String sDate, String fromFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, Locale.getDefault());
        Date date;
        String month;
        try {
            date = dateFormat.parse(sDate);
            month = (String) android.text.format.DateFormat.format("MMM", date);
        } catch (ParseException e) {
            return "";
        }
        return month;
    }

    public static String previousDay(String sDate, String fromFormat, String toFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, Locale.getDefault());
        try {
            Date date = dateFormat.parse(sDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            SimpleDateFormat returnDateFormat = new SimpleDateFormat(toFormat, Locale.getDefault());
            String yesterdayAsString = returnDateFormat.format(calendar.getTime());

            return yesterdayAsString;
        } catch (ParseException e) {
            return "";
        }
    }

    public static String nextDay(String sDate, String fromFormat, String toFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, Locale.getDefault());
        try {
            Date date = dateFormat.parse(sDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            SimpleDateFormat returnDateFormat = new SimpleDateFormat(toFormat, Locale.getDefault());
            String yesterdayAsString = returnDateFormat.format(calendar.getTime());

            return yesterdayAsString;
        } catch (ParseException e) {
            return "";
        }
    }

    public static String DateFormatConverterGMT(String date, String fromFormat, String toFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(toFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("gmt"));
        String newFormat = formatter.format(testDate);
        System.out.println(".....Date in gmt..." + newFormat);
        return newFormat;
    }

    public static String DateFormatConverterLocal(String date, String fromFormat, String toFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("gmt"));
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(toFormat);
        formatter.setTimeZone(TimeZone.getDefault());
        String newFormat = formatter.format(testDate);
        System.out.println(".....Date in local..." + newFormat);
        return newFormat;
    }

    public static int DateCompare(String dateFormat, String Date1, String Date2) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


            Date date1 = formatter.parse(Date1);


            Date date2 = formatter.parse(Date2);

            return date1.compareTo(date2);


        } catch (ParseException e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    public static String ConvertTimeUTC(String time) {

        String[] timeArray = time.trim().split(":");
        int hours = Integer.parseInt(timeArray[0]);

        int minutes;
        try {
            minutes = Integer.parseInt(timeArray[1].substring(0, 2));
        } catch (NumberFormatException e) {
            minutes = Integer.parseInt(timeArray[1].substring(0, 1));
        }

        String state = time.split(" ")[1];
        if (state.equalsIgnoreCase("PM")) {
            hours = hours + 12;
        }

        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getRawOffset();
        if (offset <= 0) {
            hours += (offset / 3600000);
            minutes += ((offset / 60000) % 60);
            if (minutes > 60) {
                minutes = minutes - 60;
                hours++;
            }

        } else {
            hours -= (offset / 3600000);
            if (hours < 0) {
                hours = 24 + hours;
            }
            minutes -= ((offset / 60000) % 60);
            if (minutes < 0) {
                minutes = 60 + minutes;
                hours--;
            }
        }

        if (hours > 12) {
            if (hours > 24) {
                hours -= 24;
                if (state.equalsIgnoreCase("AM")) {
                    state = "PM";
                } else {
                    state = "AM";
                }
            } else {
                hours -= 12;
                state = "PM";
            }

        } else {
            state = "AM";
        }


        return hours + ":" + minutes + " " + state;
    }

    public static String ConvertTimeLocal(String time) {

        String[] timeArray = time.trim().split(":");
        int hours = Integer.parseInt(timeArray[0]);

        int minutes;
        try {
            minutes = Integer.parseInt(timeArray[1].substring(0, 2));
        } catch (NumberFormatException e) {
            minutes = Integer.parseInt(timeArray[1].substring(0, 1));
        }

        String state = time.split(" ")[1];
        if (state.equalsIgnoreCase("PM")) {
            hours = hours + 12;
        }

        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getRawOffset();
        if (offset >= 0) {
            hours += (offset / 3600000);
            minutes += ((offset / 60000) % 60);
            if (minutes > 60) {
                minutes = minutes - 60;
                hours++;
            }

        } else {
            hours -= (offset / 3600000);
            if (hours < 0) {
                hours = 24 + hours;
            }
            minutes -= ((offset / 60000) % 60);
            if (minutes < 0) {
                minutes = 60 + minutes;
                hours--;
            }

        }


        if (hours > 12) {
            if (hours > 24) {
                hours -= 24;
                if (state.equalsIgnoreCase("AM")) {
                    state = "PM";
                } else {
                    state = "AM";
                }
            } else {
                hours -= 12;
                state = "PM";
            }

        } else {
            state = "AM";
        }

        return hours + ":" + minutes + " " + state;
    }

    public static String TimeFormatConverter(String time, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat fromSDF = new SimpleDateFormat(fromFormat);
            SimpleDateFormat toSDF = new SimpleDateFormat(toFormat);
            Date fromDt = fromSDF.parse(time);
            return toSDF.format(fromDt);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String convertDateTimeZone(String newDate) {
        if(!TextUtils.isEmpty(newDate)) {
            String date=newDate.replace("-","/");
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date sdate = null;
            try {
                sdate = format.parse(date);
                System.out.println(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TimeZone timezone = TimeZone.getDefault();
            Date now = new Date();
            int seconds = timezone.getOffset(now.getTime()) / 1000;

            Calendar gc1 = new GregorianCalendar();
            gc1.setTime(sdate);
            gc1.add(Calendar.SECOND, seconds);
            Date d1 = gc1.getTime();

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            String reportDate = df.format(d1);

            // Print what date is today!
            System.out.println("Report Date: " + reportDate);

            return reportDate;
        }
        return null;
    }

    public static String formatDate(String date)
    {
        String newDate=date.replace("-","/");
        try {
            SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");

            return format.format(Date.parse(newDate));
        }catch (Exception e){}
        return "";
    }

    public static String eventDateFormat(String date)
    {
        String newDate=date.replace("-","/");
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return format.format(Date.parse(newDate));
        }catch (Exception e){}
        return "";
    }

    public static String getTime(String date)
    {
        String newDate=date.replace("-","/");
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            return format.format(Date.parse(newDate));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static Date stringToDate(String date)
    {
        String newDate=date.replace("-","/");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            return format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
