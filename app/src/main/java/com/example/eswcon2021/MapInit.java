package com.example.eswcon2021;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

public class MapInit extends AppCompatActivity implements View.OnClickListener, com.google.android.gms.location.LocationListener {

    public boolean locationPermission = false;
    private static final int PERMISSIONS_REQUEST_ENABLE_GPS = 448;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 449;
    private static final int ERROR_DIALOG_REQUEST = 450;

    private FusedLocationProviderClient mFusedLocation;
    private FirebaseFirestore mDb;
    public UserLocation userLocation;

    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private static final String TAG = "MapInit";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "ONCREATE MAPINIT");
        setContentView(R.layout.activity_homepage);

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);

        // A Firestore instance
        mDb = FirebaseFirestore.getInstance();
    }

    // The below code will be for finding the user location and sending it to the Firestore

    private void saveUserLocation() {
        if (userLocation != null) {
            DocumentReference locationRef = mDb.collection(getString(R.string.collection_user_locations)).document(FirebaseAuth.getInstance().getUid());
            locationRef.set(userLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "SAVE USER LOCATION MAPINIT");
                    }
                }
            });
        }
    }

    private void getLastKnownLocation() {



        Log.d(TAG, "LAST KNOWN LOCATION MAPINIT");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "LAST KNOWN LOCATION MAPINIT 2");
            return;
        }
            Log.d(TAG, "LAST KNOWN LOCATION MAPINIT 3");

            mFusedLocation.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>(){
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if(task.isSuccessful())
                    {
                        Log.d(TAG, "LAST KNOWN LOCATION MAPINIT 4");
                        Location location = task.getResult();

                        if(location == null)
                        {
                            locationRequest = LocationRequest.create();
                            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                            locationRequest.setInterval(20 * 1000);
                            locationCallback = new LocationCallback() {
                                @Override
                                public void onLocationResult(LocationResult locationResult) {
                                    if (locationResult == null) {
                                        return;
                                    }
                                }
                            };
                        }

                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        GeoPoint geopoint = new GeoPoint(latitude, longitude);

                        Log.d(TAG, "ONCOMPLETE: LATITUDE" + +geopoint.getLatitude());
                        Log.d(TAG, "ONCOMPLETE: LONGITUDE" + +geopoint.getLongitude());
                        userLocation.setGeoPoint(geopoint);
                        userLocation.setTimestamp(null); // Insert a user timestamp
                        saveUserLocation();
                    }
                }
            });
    }

    private void getUserDetails() {
        Log.d(TAG, "USER DETAILS MAPINIT");
        if (userLocation == null) {
            Log.d(TAG, "USER DETAILS 2 MAPINIT");
            userLocation = new UserLocation();

            DocumentReference userRef = mDb.collection(getString(R.string.collection_users)).document(FirebaseAuth.getInstance().getUid());

            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        User user = task.getResult().toObject(User.class);
                        userLocation.setUser(user);
                        ((UserClient)(getApplicationContext())).setUser(user);
                        getLastKnownLocation();
                    }
                }
            });
        }
        else
        {
            getLastKnownLocation();
        }
    }

    // All of the above code is for initializing the maps on the phone

    private boolean checkMapServices() {
        Log.d(TAG, "MAP SERVICES MAPINIT");
        if (isServicesOK()) {
            if (isMapsEnabled()) {
                getUserDetails();
                finish();
                startActivity(new Intent(MapInit.this, Homepage.class));
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this); // Verify this.getContext() works!
        builder.setMessage("The Map feature requires GPS, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = ((LocationManager) getSystemService(Context.LOCATION_SERVICE));

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() { // Request location permission, so that we can get the location of the device. The result of the permission request is handled by onRequestPermissionsResult.
        if (ContextCompat.checkSelfPermission(this ,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermission = true;
            getUserDetails();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK() { // Determining if the device is able to use Google Services - this is simply a debug method

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapInit.this); // Verify this.getContext() works!

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occurred but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapInit.this, available, ERROR_DIALOG_REQUEST); // Verify this.getContext() works!
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, // The request is asked and sent to this method to keep or change locationPermission
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        locationPermission = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermission = true;
                    getUserDetails();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // Same thing as onResume, but the start
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if (locationPermission) {
                    getUserDetails();
                } else {
                    getLocationPermission();
                }
            }
        }

    }

    public void onResume() // if location permissions are good, jump into the mapAccess
    {
        super.onResume();
        if (checkMapServices()) {
            if (locationPermission) {
                getUserDetails();
            } else {
                getLocationPermission();
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause()
    {
        super.onPause();
        //locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}