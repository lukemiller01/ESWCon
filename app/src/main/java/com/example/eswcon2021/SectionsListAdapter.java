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

// This adapter is used in conjunction with the Schedule fragment
// For each day, inputs the correct time, date, locations
// In the form of strings
public class SectionsListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView event, time, location;
    private String[] eventArray, timeArray, locationArray;

    public SectionsListAdapter(Context c, String[] e, String[] t, String[] l)
    {
        mContext = c;
        eventArray = e;
        timeArray = t;
        locationArray = l;
        layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return eventArray.length;
    }

    @Override
    public Object getItem(int position) {
        return eventArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.schedule_single_item, parent, false); // Changed this line
        }

        event = convertView.findViewById(R.id.textview);
        time = convertView.findViewById(R.id.textview2);
        location = convertView.findViewById(R.id.textview3);

        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.sourcesansproregular);

        event.setTypeface(typeface);
        event.setTextColor(Color.parseColor("#000000"));

        time.setTypeface(typeface);
        time.setTextColor(Color.parseColor("#000000"));

        location.setTypeface(typeface);
        location.setTextColor(Color.parseColor("#000000"));


        event.setText(eventArray[position]);
        time.setText(timeArray[position]);
        location.setText(locationArray[position]);


        return convertView;
    }
}
