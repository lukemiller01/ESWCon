package com.example.eswcon2021;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class mapAccess extends Fragment implements OnMapReadyCallback {

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mMapView;

    private GoogleMap googleMap;
    private LatLngBounds bounds;
    //private UserLocation position;
    private ArrayList<Integer> positionArray = new ArrayList<>();
    private ArrayList<UserLocation> mUserLocations = new ArrayList<>();
    private ArrayList<User> mUserList = new ArrayList<>();
    /*
    private int images[] = {
            R.drawable.solarchargingstation,
            R.drawable.clubspace,
            R.drawable.solarstrand,
            R.drawable.letchworth,
            R.drawable.solarsmoothie,
            R.drawable.solarboat,
            R.drawable.composting,
            R.drawable.sustainability,
            R.drawable.parks1,
            R.drawable.growhome,
            R.drawable.parks1,
            R.drawable.parks1,
            R.drawable.parks1,
            R.drawable.parks1
    };
     */
    public static SharedPreferences sharedPreferences2;
    public static String selectedLocation;


    MarkerOptions options1 = new MarkerOptions();

    private static final String TAG = "mapAccess";

    public mapAccess() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mUserLocations.size() == 0) { // make sure the list doesn't duplicate by navigating back
            if (getArguments() != null) {
            }
        }

        sharedPreferences2 = getActivity().getSharedPreferences("Location", Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_map_access, container, false);
        mMapView = view.findViewById(R.id.mapView);

        initGoogleMap(savedInstanceState);

        return view;

    }

    private void setCameraView()
    {
        double bottomBoundary = 43.000638 - .1;
        double leftBoundary = -78.790069 - .1;
        double topBoundary = 43.000638 + .1;
        double rightBoundary = -78.790069 + .1;

        bounds = new LatLngBounds(new LatLng(bottomBoundary, leftBoundary), new LatLng(topBoundary, rightBoundary));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
    }

    private void dropMarkers()
    {
        // Solar Charging Station
        Marker options1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.001036, -78.787166))
                .title("ESWUB Solar Charging Station")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options1.setTag(1);

        // ESWUB Club Space
        Marker options2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.001436, -78.787161))
                .title("ESWUB Club Space")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options2.setTag(2);

        // UB Solar Strand
        Marker options3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.993649, -78.790576))
                .title("UB Solar Strand ")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options3.setTag(3);

        // Letchworth Woods
        Marker options4 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.007597, -78.791155))
                .title("Letchworth Woods")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options4.setTag(4);

        // Solar Project - Solar Smoothie Cart
        Marker options5 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.002293, -78.787234))
                .title("Solar Project - Solar Smoothie Cart ")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options5.setTag(5);

        // Solar Project - Solar Boat
        Marker options6 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.005005, -78.782982))
                .title("Solar Project - Solar Boat")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options6.setTag(6);

        // UB composting station
        Marker options7 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.993241, -78.794378))
                .title("UB composting station")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options7.setTag(7);

        // UB Sustainability office
        Marker options8 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.993886, -78.794471))
                .title("UB Sustainability office")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options8.setTag(8);

        // Parks Project - Rainwater Collection Structure
        Marker options9 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.994186, -78.793880))
                .title("Parks Project - Rainwater Collection Structure")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options9.setTag(9);

        // GRoW Home
        Marker options10 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(45.951801, - 78.820719))
                .title("GRoW Home")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options10.setTag(10);

        // Parks Project - Pergola
        Marker options11 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.934266, -78.831834))
                .title("Parks Project - Pergola")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options11.setTag(11);

        // Parks Project - Pallet Benches 1
        Marker options12 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.952620, -78.829517))
                .title("Parks Project - Pallet Benches 1")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options12.setTag(12);

        // Parks Project - Pallet Benches 2
        Marker options13 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.950906, -78.826709))
                .title("Parks Project - Pallet Benches 2")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options13.setTag(13);

        // Parks/Solar Collaboration - Solar Sprouts
        Marker options14 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.911596, -78.870976))
                .title("Parks/Solar Collaboration - Solar Sprouts")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        options14.setTag(14);
    }

    private void initGoogleMap(Bundle savedInstanceState)
    {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        googleMap = map;
        dropMarkers();
        setCameraView();
        listener();
    }

    private void listener() {
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int position = (int) (marker.getTag());
                if (position == 1) {
                    sharedPreferences2.edit().putString(selectedLocation, "loc1").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 2)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc2").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 3)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc3").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 4)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc4").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 5)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc5").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 6)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc6").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 7)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc7").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 8)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc8").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 9)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc9").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 10)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc10").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 11)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc11").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 12)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc12").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 13)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc13").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }
                else if(position == 14)
                {
                    sharedPreferences2.edit().putString(selectedLocation, "loc14").apply();
                    startActivity(new Intent(getActivity(), MapDetail.class));
                }

                return false;
            }
        });
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
