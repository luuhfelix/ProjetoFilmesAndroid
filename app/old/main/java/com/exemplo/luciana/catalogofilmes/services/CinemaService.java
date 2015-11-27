package com.exemplo.luciana.catalogofilmes.services;

import com.exemplo.luciana.catalogofilmes.model.Cinema;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciana on 14/11/2015.
 */
public class CinemaService extends WebService {

    public List<Cinema> doService() throws Exception {
        JSONArray response = this.getJson("https://api.myjson.com/bins/4135h", "cinemas");

        List<Cinema> encontrados = new ArrayList<Cinema>();

        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Cinema cinema = new Cinema();
                cinema.setNome(obj.getString("nome"));
                cinema.setLat(obj.getDouble("lat"));
                cinema.setLongi(obj.getDouble("long"));
                encontrados.add(cinema);
            }

        } catch (JSONException e) {
            // handle exception
        }

        return encontrados;
    }
}
