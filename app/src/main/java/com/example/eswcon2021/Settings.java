package com.example.eswcon2021;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class Settings extends Fragment {

    private ListView listView;
    public static SharedPreferences settingsSP;
    public static String rootChangeOne = "false";


    public Settings() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Setting the back button
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        listView = view.findViewById(R.id.settingslist);

        setupListView();

        settingsSP = getActivity().getSharedPreferences("SettingsSP", Context.MODE_PRIVATE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        startActivity(new Intent(getActivity(), About.class));
                        break;
                    case 1:
                        rootChangeOne = "true";
                        settingsSP.edit().putString(rootChangeOne, "true").apply();
                        startActivity(new Intent(getActivity(), forgotPassword.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), Help.class));
                        break;
                }
            }
        });

        return view;
    }

    private void setupListView() {

        String[] title = getResources().getStringArray(R.array.Settings);

        SectionsSettingsAdapter adapter = new SectionsSettingsAdapter(getActivity(), title); // Replaced from "this"
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        rootChangeOne = "false";
        settingsSP.edit().putString(rootChangeOne, "true").apply();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
