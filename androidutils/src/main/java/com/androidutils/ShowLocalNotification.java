package com.androidutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Admin on 22-04-2016.
 */
public class ShowLocalNotification {

    public static int showNotification(Context context, int notificationIconResourceId, String title, String message, int notificationId){
            NotificationCompat.Builder b = new NotificationCompat.Builder(context);

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(notificationIconResourceId)
                    .setTicker("Hearty365")
                    .setContentTitle(title)
                    .setContentText(message)
                    .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, b.build());
            return notificationId;
        }

    public static void cancelNotification(Context context, int notificationId){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }
}
