package com.androidutils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarColor {

    //Applicable for API level 21 and above
    public static void setStatusBarColor(Activity activity, int color)
    {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= 21){
            window.setStatusBarColor(color);
        }
    }

    public static void setStatusBarTransparent(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= 21){
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
