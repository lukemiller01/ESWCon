package com.example.eswcon2021;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BroadcastService extends Service
{
    public static String tTitle1;
    public static String tMessage1;

    public static SharedPreferences sharedPreferences1;
    public static SharedPreferences sharedPreferences2;

    private static BroadcastReceiver m_ScreenOffReceiver;

    private static final String TAG = "PostReq";

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {

        Log.d(TAG, "1");
        sharedPreferences1 = getSharedPreferences("Title", Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("Body", Context.MODE_PRIVATE);
        registerScreenOffReceiver();
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(m_ScreenOffReceiver);
        m_ScreenOffReceiver = null;
    }

    private void registerScreenOffReceiver()
    {
        m_ScreenOffReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                Log.d(TAG, "2");
                Bundle extras = intent.getExtras();
                tTitle1 =  extras.getString("title");
                tMessage1 = extras.getString("body");

                Intent intent2 = new Intent("update");
                intent2.putExtra("title", tTitle1);
                intent2.putExtra("body", tMessage1);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction("com.push.message.received");
        registerReceiver(m_ScreenOffReceiver, filter);
    }

}