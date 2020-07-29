package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ScheduleDetailedSun extends Activity {

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
        String selectedEvent = ScheduleSunday.sharedPreferences.getString(ScheduleSunday.selectedEvent, null);

        if (selectedEvent.equalsIgnoreCase("slot1"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSunday)[0];
            preferredTime = getResources().getStringArray(R.array.TimeSunday)[0];
            preferredLocation = getResources().getStringArray(R.array.LocationSunday)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSunday)[0];
        }
        else if(selectedEvent.equalsIgnoreCase("slot2"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSunday)[1];
            preferredTime = getResources().getStringArray(R.array.TimeSunday)[1];
            preferredLocation = getResources().getStringArray(R.array.LocationSunday)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSunday)[1];
        }
        else if(selectedEvent.equalsIgnoreCase("slot3"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSunday)[2];
            preferredTime = getResources().getStringArray(R.array.TimeSunday)[2];
            preferredLocation = getResources().getStringArray(R.array.LocationSunday)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSunday)[2];
        }

        SectionsEventAdapter adapter = new SectionsEventAdapter(this, preferredEvent, preferredTime, preferredLocation, preferredDescription); // Replaced from "this"
        listview.setAdapter(adapter);
    }
}