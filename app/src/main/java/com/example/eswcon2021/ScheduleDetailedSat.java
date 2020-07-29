package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ScheduleDetailedSat extends Activity {

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
        String selectedEvent = ScheduleSaturday.sharedPreferences.getString(ScheduleSaturday.selectedEvent, null);

        if (selectedEvent.equalsIgnoreCase("slot1"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[0];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[0];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[0];
        }
        else if(selectedEvent.equalsIgnoreCase("slot2"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[1];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[1];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[1];
        }
        else if(selectedEvent.equalsIgnoreCase("slot3"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[2];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[2];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[2];
        }
        else if(selectedEvent.equalsIgnoreCase("slot4"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[3];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[3];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[3];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[3];
        }
        else if(selectedEvent.equalsIgnoreCase("slot5"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[4];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[4];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[4];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[4];
        }
        else if(selectedEvent.equalsIgnoreCase("slot6"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[5];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[5];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[5];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[5];
        }
        else if(selectedEvent.equalsIgnoreCase("slot7"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsSaturday)[6];
            preferredTime = getResources().getStringArray(R.array.TimeSaturday)[6];
            preferredLocation = getResources().getStringArray(R.array.LocationSaturday)[6];
            preferredDescription = getResources().getStringArray(R.array.DescriptionSaturday)[6];
        }

        SectionsEventAdapter adapter = new SectionsEventAdapter(this, preferredEvent, preferredTime, preferredLocation, preferredDescription); // Replaced from "this"
        listview.setAdapter(adapter);
    }
}