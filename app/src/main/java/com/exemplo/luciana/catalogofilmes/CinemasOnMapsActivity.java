package com.exemplo.luciana.catalogofilmes;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.exemplo.luciana.catalogofilmes.model.Cinema;
import com.exemplo.luciana.catalogofilmes.services.CinemaService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class CinemasOnMapsActivity extends FragmentActivity  implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemas_on_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        try {
            CinemaService ws = new CinemaService();
            List<Cinema> cinemas = ws.execute().get();

            for(Cinema marker:cinemas){
                map.addMarker(new MarkerOptions().position(new LatLng(marker.getLat(),marker.getLongi())).title(marker.getNome()));
            }

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener gps = new GPS(map,cinemas);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gps);

        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}
