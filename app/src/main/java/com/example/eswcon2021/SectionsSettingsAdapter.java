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

public class SectionsSettingsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView title;
    private String[] titleArray;

    public SectionsSettingsAdapter(Context c, String[] t)
    {
        mContext = c;
        titleArray = t;
        layoutInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Override
    public Object getItem(int position) {
        return titleArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.settings_single_item, parent, false); // Changed this line
        }

        title = convertView.findViewById(R.id.textview111);

        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.sourcesansproregular);

        title.setTypeface(typeface);
        title.setTextColor(Color.parseColor("#000000"));

        title.setText(titleArray[position]);


        return convertView;
    }
}
