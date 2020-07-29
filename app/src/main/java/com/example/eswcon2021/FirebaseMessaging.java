package com.example.eswcon2021;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

public class FirebaseMessaging extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessaging";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived");

        if(remoteMessage.getData().size() > 0)
        {
            showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
        }

        if(remoteMessage.getData().isEmpty())
        {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        Intent intent = new Intent("com.push.message.received");
        intent.putExtra("title", remoteMessage.getData().get("title"));
        intent.putExtra("body", remoteMessage.getData().get("body"));
        sendBroadcast(intent);
    }



    private void showNotification(String title, String description)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Log.d(TAG, "onMessageReceived");

        NotificationManager notifMan = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String notifChannelID = "com.example.eswcon2021";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notifChannel = new NotificationChannel(notifChannelID, "Notification", NotificationManager.IMPORTANCE_DEFAULT);

            notifChannel.setDescription("ESWCON Channel");
            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.GREEN);
            notifChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notifMan.createNotificationChannel(notifChannel);
        }

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, notifChannelID);

        notifBuilder.setAutoCancel(true);
        notifBuilder.setDefaults(Notification.DEFAULT_ALL);
        notifBuilder.setWhen(System.currentTimeMillis());
        notifBuilder.setSmallIcon(R.drawable.gear);
        notifBuilder.setContentTitle(title);
        notifBuilder.setContentText(description);
        notifBuilder.setContentInfo("Info");
        notifBuilder.setContentIntent(pendingIntent);

        notifMan.notify(new Random().nextInt(), notifBuilder.build());
    }
}