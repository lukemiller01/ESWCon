package com.example.eswcon2021;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class Homepage extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private static final String TAG = "Homepage";

    Toolbar toolbar;
    public static boolean logout;
    int intentFrag = 0;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);
        // Setting up the homepage fragment
        // Setting up the bottom navigation view
        //setContentView(R.layout.activity_homepage);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ESWCon");

        BottomNavigationView tab = findViewById(R.id.bottomNav);
        tab.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        logout = false;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intentFrag = getIntent().getExtras().getInt("frgToLoad");
            if(intentFrag == 1)
            {
                Log.d(TAG, "Passpass");
                Settings s = new Settings();
                getSupportFragmentManager();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction t = fragmentManager.beginTransaction();
                t.replace(R.id.container, s);
                t.addToBackStack(null);
                t.commit();
            }
        }
    }

    public static boolean logout() {
        return logout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Settings s = new Settings();
                getSupportFragmentManager();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction t = fragmentManager.beginTransaction();
                t.replace(R.id.container, s);
                t.addToBackStack(null);
                t.commit();
                break;

            case R.id.logout2:
                logout = true;
                finish();
                Bundle b = new Bundle();
                b.putInt("place", 1);
                Intent i2 = new Intent(Homepage.this, MainActivity.class);
                i2.putExtras(b);
                startActivity(i2);
                break;

            case R.id.slack:
                Intent i = getPackageManager().getLaunchIntentForPackage("com.Slack");
                startActivity(i);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment f = null;

                    switch( item.getItemId() )
                    {
                        case R.id.home:
                        f = new HomeFragment();
                                break;

                        case R.id.map:
                                f = new mapAccess();
                            break;

                        case R.id.content:
                            f = new ContentFragment();
                            break;

                        case R.id.schedule:
                            f = new ScheduleFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();

                    return true;
                }
            };

    // These methods go with the implementation for fragment manager (Back stack changed listener)
    // Back button navigation in fragments

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
