package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ScheduleDetailedWed extends Activity {

    private ListView listview;

    private String preferredEvent;
    private String preferredTime;
    private String preferredLocation;
    private String preferredDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.schedule_detail);

        // Pulling up a listview of a single item
        listview = findViewById(R.id.listviewdetail);
        setupListView();

        // For the smaller window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.6));
    }

    private void setupListView()
    {
        String selectedEvent = ScheduleWednesday.sharedPreferences.getString(ScheduleWednesday.selectedEvent, null);

        if (selectedEvent.equalsIgnoreCase("slot1"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsWednesday)[0];
            preferredTime = getResources().getStringArray(R.array.TimeWednesday)[0];
            preferredLocation = getResources().getStringArray(R.array.LocationWednesday)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionWednesday)[0];
        }
        else if(selectedEvent.equalsIgnoreCase("slot2"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsWednesday)[1];
            preferredTime = getResources().getStringArray(R.array.TimeWednesday)[1];
            preferredLocation = getResources().getStringArray(R.array.LocationWednesday)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionWednesday)[1];
        }
        else if(selectedEvent.equalsIgnoreCase("slot3"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsWednesday)[2];
            preferredTime = getResources().getStringArray(R.array.TimeWednesday)[2];
            preferredLocation = getResources().getStringArray(R.array.LocationWednesday)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionWednesday)[2];
        }
        else if(selectedEvent.equalsIgnoreCase("slot4"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsWednesday)[3];
            preferredTime = getResources().getStringArray(R.array.TimeWednesday)[3];
            preferredLocation = getResources().getStringArray(R.array.LocationWednesday)[3];
            preferredDescription = getResources().getStringArray(R.array.DescriptionWednesday)[3];
        }
        else if(selectedEvent.equalsIgnoreCase("slot5")) {
            preferredEvent = getResources().getStringArray(R.array.EventsWednesday)[4];
            preferredTime = getResources().getStringArray(R.array.TimeWednesday)[4];
            preferredLocation = getResources().getStringArray(R.array.LocationWednesday)[4];
            preferredDescription = getResources().getStringArray(R.array.DescriptionWednesday)[4];
        }

        SectionsEventAdapter adapter = new SectionsEventAdapter(this, preferredEvent, preferredTime, preferredLocation, preferredDescription);
        listview.setAdapter(adapter);
    }
}