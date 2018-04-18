package com.example.heloise.mqtt123;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    Double longi, lat;
//    Double mLat, mLong;
    LatLng mPosition;
    Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Paste callbacks here, parse JSON object to get lat, long values
        // onMessageRecvd: if mMap is not null, parse the JSON values
        // add a marker to the map





//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        Bundle bundle = getIntent().getExtras();
//        lat = bundle.getDouble("lat");
//        longi =  bundle.getDouble("long");

//        String value1=bundle.getString("message");
//        JsonParser parser = new JsonParser();
//        JsonObject json = (JsonObject) parser.parse(value1);
//        lat = String.valueOf(json.get("lat"));
//        longi = String.valueOf(json.get("long"));

          lat = 15.4884442;
          longi = 73.8171119;

         mPosition = new LatLng(lat, longi);

         new CountDownTimer(5000, 1000) {
             @Override
             public void onTick(long l) {
                 // Paste this code inside onMessageRecieved
                 // Removed countdown timer
                 lat = 15.3929768 ;
                 longi = 73.7820748;
                 marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, longi)));
             }

             @Override
             public void onFinish() {

             }
         }.start();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(this, 1,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);

        }
//        LatLng sydney = new LatLng(lat,longi);

        marker = mMap.addMarker(new MarkerOptions().position(mPosition).title("Marker in Sydney"));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(Location location){
        Toast.makeText(this, "Curren location:\n" + location, Toast.LENGTH_LONG).show();
    }
}
