package com.exemplo.luciana.catalogofilmes;

import com.exemplo.luciana.catalogofilmes.model.Cinema;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Luciana on 14/11/2015.
 */
public class Calculation {

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;

        return valueResult;
    }

    public Cinema nearCinema(LatLng currentLocation, List<Cinema> cinemas){
        Cinema near = null;
        double lastDistance = 0;
        for (Cinema c: cinemas) {
            LatLng cinemaLocation = new LatLng(c.getLat(),c.getLongi());
            double currentDistance = this.CalculationByDistance(currentLocation, cinemaLocation);
            c.setDistance(currentDistance);
            if(currentDistance < lastDistance) {
                near = c;
            }
            lastDistance = currentDistance;
        }

        return near;
    }

}