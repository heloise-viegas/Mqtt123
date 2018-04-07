package com.example.heloise.mqtt123;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String l1="",l2="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Bundle bundle = getIntent().getExtras();
        String value1=bundle.getString("message");
        Log.d("print:",value1);
        String s1[]=value1.split(",");
        Log.d("print1",s1[0]);
        Log.d("print1",s1[1]);
        l1=s1[0].substring(7);
         l2=s1[1].substring(7,17);
        Log.d("print2",l1);
        Log.d("print2",l2);



       // double value=Double.parseDouble(value1);
       // Log.d("print:", String.valueOf(value));


        //new
       /* JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(String.valueOf(bundle)).getAsJsonObject();
        latt=jsonObject.getAsJsonObject("lat");
        v=latt.getAsInt();
        Log.d("message", String.valueOf((v)));*/

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

        // Add a marker in Sydney and move the camera
        //double d1=Double.parseDouble(l1);
        //double d2=Double.parseDouble(l2);
        LatLng sydney = new LatLng(Double.valueOf(l1),Double.valueOf(l2));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
