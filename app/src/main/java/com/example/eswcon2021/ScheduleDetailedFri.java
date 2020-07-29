package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ScheduleDetailedFri extends Activity {

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
        String selectedEvent = ScheduleFriday.sharedPreferences.getString(ScheduleFriday.selectedEvent, null);

        if (selectedEvent.equalsIgnoreCase("slot1"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[0];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[0];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[0];
        }
        else if(selectedEvent.equalsIgnoreCase("slot2"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[1];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[1];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[1];
        }
        else if(selectedEvent.equalsIgnoreCase("slot3"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[2];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[2];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[2];
        }
        else if(selectedEvent.equalsIgnoreCase("slot4"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[3];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[3];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[3];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[3];
        }
        else if(selectedEvent.equalsIgnoreCase("slot5"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[4];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[4];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[4];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[4];
        }
        else if(selectedEvent.equalsIgnoreCase("slot6"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[5];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[5];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[5];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[5];
        }
        else if(selectedEvent.equalsIgnoreCase("slot7"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[6];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[6];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[6];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[6];
        }
        else if(selectedEvent.equalsIgnoreCase("slot8"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[7];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[7];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[7];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[7];
        }
        else if(selectedEvent.equalsIgnoreCase("slot9"))
        {
            preferredEvent = getResources().getStringArray(R.array.EventsFriday)[8];
            preferredTime = getResources().getStringArray(R.array.TimeFriday)[8];
            preferredLocation = getResources().getStringArray(R.array.LocationFriday)[8];
            preferredDescription = getResources().getStringArray(R.array.DescriptionFriday)[8];
        }

        SectionsEventAdapter adapter = new SectionsEventAdapter(this, preferredEvent, preferredTime, preferredLocation, preferredDescription); // Replaced from "this"
        listview.setAdapter(adapter);
    }

}