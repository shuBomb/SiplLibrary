package com.androidutils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 10-02-2016.
 */
public class GetCrashLog {
    private static final String TAG = "GetCrashLog";
    private static GetCrashLog mInstance = null;

    StringBuffer report;

    public GetCrashLog(Context context) {

    }
    static Context context;

    public static GetCrashLog getInstance(Context mContext) {
        if (mInstance == null) {

            mInstance = new GetCrashLog(mContext);
        }
        context = mContext;
        return mInstance;
    }

    public StringBuffer getCrashLog() {
        report = new StringBuffer();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, final Throwable e) {
                StackTraceElement[] arr = e.getStackTrace();
                report = new StringBuffer(e.toString());
                final String lineSeperator = "\n\n";
                report.append(lineSeperator);
                report.append("--------- Stack trace ---------\n\n");
                for (int i = 0; i < arr.length; i++) {
                    report.append("    ");
                    report.append(arr[i].toString());
                    report.append(lineSeperator);
                }
                report.append(lineSeperator);
                // If the exception was thrown in a background thread inside
                // AsyncTask, then the actual exception can be found with getCause
                report.append("--------- Cause ---------\n\n");
                Throwable cause = e.getCause();
                if (cause != null) {
                    report.append(cause.toString());
                    report.append(lineSeperator);
                    arr = cause.getStackTrace();
                    for (int i = 0; i < arr.length; i++) {
                        report.append("    ");
                        report.append(arr[i].toString());
                        report.append(lineSeperator);
                    }
                }
                report.append(lineSeperator);
                report.append("--------- Cause ---------\n\n");
                report.append(e.getMessage());
                // Getting the Device brand,model and sdk verion details.
                report.append(lineSeperator);
                report.append("--------- Device ---------\n\n");
                report.append("Brand: ");
                report.append(Build.BRAND);
                report.append(lineSeperator);
                report.append("Device: ");
                report.append(Build.DEVICE);
                report.append(lineSeperator);
                report.append("Model: ");
                report.append(Build.MODEL);
                report.append(lineSeperator);
                report.append("Id: ");
                report.append(Build.ID);
                report.append(lineSeperator);
                report.append("Product: ");
                report.append(Build.PRODUCT);
                report.append(lineSeperator);
                report.append(lineSeperator);
                report.append("--------- Firmware ---------\n\n");
                report.append("SDK: ");
                report.append(Build.VERSION.SDK);
                report.append(lineSeperator);
                report.append("Release: ");
                report.append(Build.VERSION.RELEASE);
                report.append(lineSeperator);
                report.append("Incremental: ");
                report.append(Build.VERSION.INCREMENTAL);
                report.append(lineSeperator);
                report.append(lineSeperator);

                Log.e("Report ::", report.toString());

                SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss",
                        Locale.getDefault());
                String pkUsersGEODetailsId = s.format(new Date());

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm a",
                        Locale.getDefault());

                String currentDatetime = sdf.format(c.getTime());

                System.exit(0);
                // If you don't kill the VM here the app goes into limbo
            }
        });
        return report;
    }
}
