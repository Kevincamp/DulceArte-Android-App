package com.example.migadepan.pharmacyapp;

import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.migadepan.pharmacyapp.Controller.CatalogActivity;
import com.example.migadepan.pharmacyapp.DataBase.DatabaseHelper;
import com.example.migadepan.pharmacyapp.Model.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class IntroMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    int idUser;
    private GoogleMap mMap;
    ArrayList<Store> pharmacies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        idUser = getIntent().getExtras().getInt("idUser");

        //Obtengo las farmacias de la base de datos
        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
        pharmacies = dbh.getStoreList();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Store p : pharmacies){
            LatLng situation = new LatLng(Double.parseDouble(p.getLatitude()), Double.parseDouble(p.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions().position(situation).title(p.getName()));
            marker.setTag(p);
        }

        // Add a marker in Sydney and move the camera
        LatLng granada = new LatLng(37.205602,-3.611477);
        float zoomLevel = 12;
        mMap.addMarker(new MarkerOptions().position(granada).title("Granada"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(granada, zoomLevel));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker arg0) {
                Store pSelected = (Store) arg0.getTag();
                Intent intent = new Intent(IntroMapsActivity.this, CatalogActivity.class);
                intent.putExtra("pharmSelected", pSelected.getId());
                intent.putExtra("idUser",idUser);
                startActivity(intent);

                return true;
            }

        });
    }
}
