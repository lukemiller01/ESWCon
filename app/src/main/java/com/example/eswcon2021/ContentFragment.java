package com.example.eswcon2021;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ContentFragment extends Fragment {

    public ContentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content,container, false);

        // SPEAKERS
        Button speakers = view.findViewById(R.id.speakers);
        speakers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentSpeakersFragment csf = new ContentSpeakersFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.container, csf);
                t.addToBackStack(null);
                t.commit();
            }
        });

        // SPONSORS
        Button sponsors = view.findViewById(R.id.sponsors);
        sponsors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentSponsorsFragment csf = new ContentSponsorsFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.container, csf);
                t.addToBackStack(null);
                t.commit();
            }
        });

        // POSTERS
        Button posters = view.findViewById(R.id.posters);
        posters.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentPostersFragment csf = new ContentPostersFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.container, csf);
                t.addToBackStack(null);
                t.commit();
            }
        });

        // MISCELLANEOUS
        Button misc = view.findViewById(R.id.misc);
        misc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentMiscFragment csf = new ContentMiscFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.container, csf);
                t.addToBackStack(null);
                t.commit();
            }
        });


        return view;
    }

}
