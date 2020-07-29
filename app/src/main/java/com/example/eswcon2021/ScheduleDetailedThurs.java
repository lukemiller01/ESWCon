package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ScheduleDetailedThurs extends Activity {

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
        String selectedEvent = ScheduleThursday.sharedPreferences.getString(ScheduleThursday.selectedEvent, null);

        if (selectedEvent.equalsIgnoreCase("slot1"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[0];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[0];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[0];
        }
        else if(selectedEvent.equalsIgnoreCase("slot2"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[1];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[1];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[1];
        }
        else if(selectedEvent.equalsIgnoreCase("slot3"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[2];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[2];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[2];
        }
        else if(selectedEvent.equalsIgnoreCase("slot4"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[3];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[3];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[3];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[3];
        }
        else if(selectedEvent.equalsIgnoreCase("slot5"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[4];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[4];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[4];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[4];
        }
        else if(selectedEvent.equalsIgnoreCase("slot6"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[5];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[5];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[5];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[5];
        }
        else if(selectedEvent.equalsIgnoreCase("slot7"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[6];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[6];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[6];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[6];
        }
        else if(selectedEvent.equalsIgnoreCase("slot8"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[7];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[7];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[7];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[7];
        }
        else if(selectedEvent.equalsIgnoreCase("slot9"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[8];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[8];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[8];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[8];
        }
        else if(selectedEvent.equalsIgnoreCase("slot10"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsThursday)[9];
            preferredTime = getResources().getStringArray(R.array.TimeThursday)[9];
            preferredLocation = getResources().getStringArray(R.array.LocationThursday)[9];
            preferredDescription = getResources().getStringArray(R.array.DescriptionThursday)[9];
        }

        SectionsEventAdapter adapter = new SectionsEventAdapter(this, preferredEvent, preferredTime, preferredLocation, preferredDescription); // Replaced from "this"
        listview.setAdapter(adapter);
    }

}