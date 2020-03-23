package com.google.firebase.capstone.herbt.project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class NearbyStore extends FragmentActivity implements OnMapReadyCallback {

    Button b1;
    Location mlocation;
    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_Code=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_store);
        b1 = (Button)findViewById(R.id.search_nearby);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        GetlastLocation();
        
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      //  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
      //          .findFragmentById(R.id.map);
      //  mapFragment.getMapAsync(this);
    }

    private void GetlastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
            return;
        }
        Task<Location>task =fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    mlocation=location;
                    Toast.makeText(getApplicationContext(),
                            mlocation.getLatitude()+""+mlocation.getLongitude(),Toast.LENGTH_SHORT).show();

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                             .findFragmentById(R.id.map);
                      mapFragment.getMapAsync(NearbyStore.this);
                }
            }
        });


    }

        @Override
    public void onMapReady(GoogleMap googleMap) {
       LatLng latLng = new LatLng(mlocation.getLatitude(),mlocation.getLongitude());
       MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here");
       googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
       googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,6));
       googleMap.addMarker(markerOptions);


       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mMap = googleMap;

               // Add a marker in Sydney and move the camera
               LatLng sydney = new LatLng(10.293013, 123.897668);
               mMap.addMarker(new MarkerOptions().position(sydney).title("Carbon"));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


               LatLng sydneys = new LatLng(10.338126, 123.894641);
               mMap.addMarker(new MarkerOptions().position(sydneys).title("dsad").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(sydneys));

               LatLng sydneyss = new LatLng(10.363990, 123.912070);
               mMap.addMarker(new MarkerOptions().position(sydneyss).title("mjkghhg"));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(sydneyss));

           }
       });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_Code:
                if(grantResults.length>0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                    GetlastLocation();
                }
                break;
        }
    }

    private void LocationStore(GoogleMap googleMap){
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
