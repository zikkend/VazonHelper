package com.example.myvazonhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public  void onReceive(Context context, Intent intent ){
        String textEvent = intent.getStringExtra("alarm event");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(textEvent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
        context.startService(intent);
    }
}