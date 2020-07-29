package com.example.eswcon2021;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ScheduleSunday extends Fragment {

    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String selectedEvent;

    public ScheduleSunday() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_schedule_sunday, container, false);
        listView = view.findViewById(R.id.listviewsun);

        setupListView();

        sharedPreferences = getActivity().getSharedPreferences("Event", Context.MODE_PRIVATE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        startActivity(new Intent(getActivity(), ScheduleDetailedSun.class));
                        sharedPreferences.edit().putString(selectedEvent, "slot1").apply();
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ScheduleDetailedSun.class));
                        sharedPreferences.edit().putString(selectedEvent, "slot2").apply();
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), ScheduleDetailedSun.class));
                        sharedPreferences.edit().putString(selectedEvent, "slot3").apply();
                        break;
                    default: break;
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void setupListView()
    {
        String[] event = getResources().getStringArray(R.array.EventsSunday);
        String[] time = getResources().getStringArray(R.array.TimeSunday);
        String[] location = getResources().getStringArray(R.array.LocationSunday);

        SectionsListAdapter adapter = new SectionsListAdapter(getActivity(), event, time, location); // Replaced from "this"
        listView.setAdapter(adapter);
    }

}