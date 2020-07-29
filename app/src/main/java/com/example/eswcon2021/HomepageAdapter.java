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

import java.util.List;

// This adapter is used in conjunction with the Schedule fragment
// For each day, inputs the correct time, date, locations
// In the form of strings
public class HomepageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView title, message;
    private List<String> titleArray, messageArray;

    public HomepageAdapter(Context c, List<String> t, List<String> m)
    {
        mContext = c;
        titleArray = t;
        messageArray = m;
        layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return titleArray.size();
    }

    @Override
    public Object getItem(int position) {
        return titleArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.homepage_single_item, parent, false);
        }

        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.sourcesansproregular);

        title = convertView.findViewById(R.id.textview11);
        title.setTypeface(typeface);
        title.setTextColor(Color.parseColor("#000000"));

        message = convertView.findViewById(R.id.textview22);
        message.setTypeface(typeface);
        message.setTextColor(Color.parseColor("#000000"));


        title.setText(titleArray.get(position));
        message.setText(messageArray.get(position));

        return convertView;
    }
}
