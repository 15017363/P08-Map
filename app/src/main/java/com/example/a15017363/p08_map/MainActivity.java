package com.example.a15017363.p08_map;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Spinner spnDirection;
    TextView tvABC;
    private GoogleMap map;
    private boolean mSpinnerInitialized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spnDirection = (Spinner)findViewById(R.id.spinner);

        tvABC = (TextView)findViewById(R.id.tvABC);
        tvABC.setText("ABC Pte Ltd \n\nWe now have 3 branches. Look below for the address and info");

        FragmentManager fm = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    return;
                }
                map.setMyLocationEnabled(true);


//                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
//                    map.setMyLocationEnabled(true);
//                } else {
//                    Log.e("GMap - Permission", "GPS access has not been granted");
//                }

                LatLng poi_north = new LatLng(1.450492, 103.814943);
                Marker pn = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 \n"
                                +"Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));



                LatLng poi_central = new LatLng(1.297802, 103.847441);
                Marker pc = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.367149, 103.928021);
                Marker pe = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));


                LatLng sg = new LatLng(1.290270, 103.851959);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sg, 10));
                CameraUpdateFactory.newLatLngZoom(sg, 10);

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(),marker.getTitle().toString(),Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        spnDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dir = (String) parent.getItemAtPosition(position);
                    if (dir.equals("North")) {
                        LatLng poi_north = new LatLng(1.450492, 103.814943);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 17));
                    } else if (dir.equals("Central")) {
                        LatLng poi_central = new LatLng(1.297802, 103.847441);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 17));
                    } else if(dir.equals("East")){
                        LatLng poi_east = new LatLng(1.367149, 103.928021);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 17));
                        Toast.makeText(getBaseContext(), "East", Toast.LENGTH_SHORT).show();
                    }else{

                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });




    }
}
