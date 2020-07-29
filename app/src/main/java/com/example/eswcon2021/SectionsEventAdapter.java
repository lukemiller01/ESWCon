package com.example.eswcon2021;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class SectionsEventAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView event, time, location, description;
    private String eventArray, timeArray, locationArray, descriptionArray;

    public SectionsEventAdapter(Context c, String e, String t, String l, String d)
    {
        mContext = c;
        eventArray = e;
        timeArray = t;
        locationArray = l;
        descriptionArray = d;
        layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.schedule_detailed_item, parent, false); // Changed this line
        }

        event = convertView.findViewById(R.id.textview01);
        time = convertView.findViewById(R.id.textview02);
        location = convertView.findViewById(R.id.textview03);
        description = convertView.findViewById(R.id.textview04);

        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.sourcesansproregular);

        event.setTypeface(typeface);
        event.setTextColor(Color.parseColor("#000000"));

        time.setTypeface(typeface);
        time.setTextColor(Color.parseColor("#000000"));

        location.setTypeface(typeface);
        location.setTextColor(Color.parseColor("#000000"));

        description.setTypeface(typeface);
        description.setTextColor(Color.parseColor("#000000"));

        event.setText(eventArray);
        time.setText(timeArray);
        location.setText(locationArray);
        description.setText(descriptionArray);

        return convertView;
    }
}