package com.androidutils;


import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.Secure;

import java.util.TimeZone;

public class GetDeviceInfo {

    public GetDeviceInfo() {
    }

    public static String getUniqueAndroidId(Context context) {

        String androidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

        return androidID;
    }

    public static String getDeviceToken(Context context){
        String device_unique_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }

    public static String getLocaleCountry(Context context){
        return  context.getResources().getConfiguration().locale.getDisplayCountry();
    }

    public static String getTimeZone(Context context){

        TimeZone tz = TimeZone.getDefault();
        return tz.getID();
    }
}