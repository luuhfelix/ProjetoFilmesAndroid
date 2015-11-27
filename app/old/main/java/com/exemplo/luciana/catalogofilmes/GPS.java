package com.exemplo.luciana.catalogofilmes;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.exemplo.luciana.catalogofilmes.model.Cinema;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Giuliano on 06/10/2015.
 */
public class GPS implements LocationListener {

    private GoogleMap map;
    private List<Cinema> otherMarkers;
    private Calculation calc = new Calculation();

    public GPS(GoogleMap map, List<Cinema> cinemas){

        this.map = map;
        this.otherMarkers = cinemas;
    }

    @Override
    public void onLocationChanged(Location location) {

        Cinema m = new Cinema(location.getLatitude(), location.getLongitude(), "");
        Double distance = calc.CalculationByDistance(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(this.otherMarkers.get(0).getLat(), this.otherMarkers.get(0).getLongi()));
        Integer km = distance.intValue();
        map.addMarker(new MarkerOptions().position(new LatLng(m.getLat(), m.getLongi())).title("I am here, "+km.toString()+"km from "+this.otherMarkers.get(0).getNome()));

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