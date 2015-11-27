package com.exemplo.luciana.catalogofilmes.services;

import android.os.AsyncTask;

import com.exemplo.luciana.catalogofilmes.model.Cinema;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciana on 14/11/2015.
 */
public class CinemaService extends AsyncTask<String, Void, List<Cinema>> {

    public List<Cinema> doService() {

        List<Cinema> encontrados = new ArrayList<Cinema>();

        try {
            WebService ws = new WebService();
            JSONArray response = ws.getJson("https://api.myjson.com/bins/4135h", "cinemas");

            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Cinema cinema = new Cinema();
                cinema.setNome(obj.getString("nome"));
                cinema.setLat(obj.getDouble("lat"));
                cinema.setLongi(obj.getDouble("long"));
                encontrados.add(cinema);
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return encontrados;
    }

    @Override
    protected List<Cinema> doInBackground(String... strings) {
        return this.doService();
    }
}
