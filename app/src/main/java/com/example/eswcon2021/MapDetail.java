package com.example.eswcon2021;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class MapDetail extends Activity {

    private ListView listview;

    private String preferredLocation, preferredDescription;
    private int preferredImage = 0;

    private static final String TAG = "MapDetail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_detail);

        // Pulling up a listview of a single item
        listview = findViewById(R.id.listviewmapdetail);
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
        String selectedMarker = mapAccess.sharedPreferences2.getString(mapAccess.selectedLocation, null);

        if (selectedMarker.equalsIgnoreCase("loc1"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[0];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[0];
            preferredImage = 1;
        }
        else if (selectedMarker.equalsIgnoreCase("loc2"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[1];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[1];
            preferredImage = 2;
        }
        else if (selectedMarker.equalsIgnoreCase("loc3"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[2];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[2];
            preferredImage = 3;
        }
        else if (selectedMarker.equalsIgnoreCase("loc4"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[3];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[3];
            preferredImage = 4;
        }
        else if (selectedMarker.equalsIgnoreCase("loc5"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[4];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[4];
            preferredImage = 5;
        }
        else if (selectedMarker.equalsIgnoreCase("loc6"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[5];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[5];
            preferredImage = 6;
        }
        else if (selectedMarker.equalsIgnoreCase("loc7"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[6];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[6];
            preferredImage = 7;
        }
        else if (selectedMarker.equalsIgnoreCase("loc8"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[7];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[7];
            preferredImage = 8;
        }
        else if (selectedMarker.equalsIgnoreCase("loc9"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[8];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[8];
            preferredImage = 9;
        }
        else if (selectedMarker.equalsIgnoreCase("loc10"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[9];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[9];
            preferredImage = 10;
        }
        else if (selectedMarker.equalsIgnoreCase("loc11"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[10];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[10];
            preferredImage = 11;
        }
        else if (selectedMarker.equalsIgnoreCase("loc12"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[11];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[11];
            preferredImage = 12;
        }
        else if (selectedMarker.equalsIgnoreCase("loc13"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[12];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[12];
            preferredImage = 13;
        }
        else if (selectedMarker.equalsIgnoreCase("loc14"))
        {
            preferredLocation = getResources().getStringArray(R.array.LocationMap)[13];
            preferredDescription = getResources().getStringArray(R.array.DescriptionMap)[13];
            preferredImage = 14;
        }

        SectionsMapAdapter adapter = new SectionsMapAdapter(this, preferredLocation, preferredDescription, preferredImage);
        listview.setAdapter(adapter);
    }
}
