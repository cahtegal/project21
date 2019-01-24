package com.hidevs.cariweton;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class FireBaseMessagingService extends FirebaseMessagingService {

    String message;
    String action;

    String title;

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i("newToken ", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification().getBody() != null) {
            Log.i("PVL", "RECEIVED MESSAGE: " + remoteMessage.getNotification().getBody());
        } else {
            Log.i("PVL", "RECEIVED MESSAGE: " + remoteMessage.getData().get("message"));
        }
       /* Log.e("GGG", "From: " + remoteMessage.getFrom());
        Log.e("GGG", "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Log.e("GGG", "Click Action: " + remoteMessage.getNotification().getClickAction() + "");
        Log.e("GGG", "Data: " + remoteMessage.getData().get("action"));
        Log.e("GGG", "Sound: " + remoteMessage.getNotification().getSound());
        Log.e("GGG", "Icon: " + remoteMessage.getNotification().getIcon());*/

        if (remoteMessage.getNotification() != null) {
            message = remoteMessage.getNotification().getBody();
            title = remoteMessage.getNotification().getTitle();
        }

        if (remoteMessage.getData().isEmpty()) {
            sendNotification(message);
        } else {
            sendNotification(remoteMessage.getData());
        }



    }

    private void sendNotification(Map<String,String> data) {

        String title = data.get("title");
        String body = data.get("body");
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent artIntent = new Intent(this, Splash.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                artIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "channel1")
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setContentInfo("Info")
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(new Random().nextInt(), mBuilder.build());
    }

    private void sendNotification(String msg) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent artIntent = new Intent(this, Splash.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                artIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "channel1")
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setContentInfo("Info")
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(new Random().nextInt(), mBuilder.build());
    }
}
