package com.example.eswcon2021;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class SectionsMapAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView title, description;
    private ImageView picture;

    private String descriptionA, titleA;
    private int imageA;

    private static final String TAG = "SectionsMapAdapter";

    public SectionsMapAdapter(Context c, String t, String d, int p)
    {
        titleA = t;
        mContext = c;
        descriptionA = d;
        imageA = p;
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
            convertView = layoutInflater.inflate(R.layout.map_single_item, parent, false); // Changed this line
        }

        title = convertView.findViewById(R.id.loctext1);
        description = convertView.findViewById(R.id.loctext2);
        picture = convertView.findViewById(R.id.locimage);

        description.setText(descriptionA);
        title.setText(titleA);
        //picture.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.solarchargingstation));

        switch (imageA) {
            case 1:
            {
                picture.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.solarchargingstation));
                break;
            }
            case 2:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.clubspace));
                break;
            }
            case 3:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.solarstrand));
                break;
            }
            case 4:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.letchworth));
                break;
            }
            case 5:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.solarsmoothie));
                break;
            }
            case 6:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.solarboat));
                break;
            }
            case 7:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.composting));
                break;
            }
            case 8:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.sustainability));
                break;
            }
            case 9:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parks1));
                break;
            }
            case 10:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.growhome));
                break;
            }
            case 11:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parks1));
                break;
            }
            case 12:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parks1));
                break;
            }
            case 13:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parks1));
                break;
            }
            case 14:
            {
                picture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.parks1));
                break;
            }

        }

        return convertView;
    }
}