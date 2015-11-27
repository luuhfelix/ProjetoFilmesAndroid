package com.exemplo.luciana.catalogofilmes;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.exemplo.luciana.catalogofilmes.model.Cinema;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Giuliano on 06/10/2015.
 */
public class GPS implements LocationListener {

    private GoogleMap map;
    private List<Cinema> otherMarkers;
    private Marker myMarker;
    private Calculation calc = new Calculation();

    public GPS(GoogleMap map, List<Cinema> cinemas){

        this.map = map;
        this.otherMarkers = cinemas;
    }

    @Override
    public void onLocationChanged(Location location) {

        LatLng myLoction = new LatLng(location.getLatitude(), location.getLongitude());
        Cinema nearCinema = calc.nearCinema(myLoction, this.otherMarkers);
        Integer km = nearCinema.getDistance().intValue();
        String title = "Voce está a "+km.toString()+"km de distância de "+nearCinema.getNome();

        if(myMarker != null) {
            myMarker.remove();
        }

        myMarker = map.addMarker(new MarkerOptions().position(myLoction).title(title));
        myMarker.showInfoWindow();
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}