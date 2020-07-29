package com.example.eswcon2021;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView listView;
    private List<String> titles;
    private List<String> messages;
    private String tTitle;
    private String tMessage;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Collections.reverse(titles);
            Collections.reverse(messages);
            Log.d(TAG, "4");
            Bundle extras = intent.getExtras();
            tTitle =  extras.getString("title");
            tMessage = extras.getString("body");
            Log.d(TAG, "title + message =" + tTitle + tMessage);
            titles.add(tTitle);
            messages.add(tMessage);
            Collections.reverse(titles);
            Collections.reverse(messages);
            saveList();
            setupUIViews();
        }
    };

    private static final String TAG = "PostReq";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent service = new Intent(getContext().getApplicationContext(), BroadcastService.class);
        getContext().getApplicationContext().startService(service);

        Log.d(TAG, "3");

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                receiver, new IntentFilter("update"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.updatelistview);

        loadList();
        setupUIViews();
        // Inflate the layout for this fragment
        return view;
    }

    public void setupUIViews()
    {
        HomepageAdapter adapter = new HomepageAdapter(getActivity(), titles, messages);
        listView.setAdapter(adapter);
    }

    private void saveList()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Notification List", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json1 = gson.toJson(titles);
        String json2 = gson.toJson(messages);
        editor.putString("Titles", json1);
        editor.putString("Messages", json2);
        editor.apply();

    }

    private void loadList() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Notification List", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json1 = sharedPreferences.getString("Titles", null);
        Type type1 = new TypeToken<ArrayList<String>>() {
        }.getType();
        titles = gson.fromJson(json1, type1);

        if (titles == null) {
            titles = new ArrayList<>();
        }

        String json2 = sharedPreferences.getString("Messages", null);
        Type type2 = new TypeToken<ArrayList<String>>() {
        }.getType();
        messages = gson.fromJson(json2, type2);

        if (messages == null) {
            messages = new ArrayList<>();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent service = new Intent(getContext().getApplicationContext(), BroadcastService.class);
        getContext().getApplicationContext().startService(service);
        loadList();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent service = new Intent(getContext().getApplicationContext(), BroadcastService.class);
        getContext().getApplicationContext().startService(service);
        loadList();
    }

    @Override
    public void onStop() {
        super.onStop();
        Intent service = new Intent(getContext().getApplicationContext(), BroadcastService.class);
        getContext().getApplicationContext().stopService(service);
        saveList();
    }

    @Override
    public void onPause() {
        super.onPause();
        // On notification refresh
        /*
        titles.clear();
        messages.clear();
         */
        saveList();
        Intent service = new Intent(getContext().getApplicationContext(), BroadcastService.class);
        getContext().getApplicationContext().stopService(service);
    }
}
