package com.exemplo.luciana.catalogofilmes.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aluno on 03/09/2015.
 */
public class WebService {
    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public JSONArray getJson(String jsonUrl, String key) throws Exception {

        URL obj = new URL(jsonUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + jsonUrl);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jobj = new JSONObject(response.toString());

        return new JSONArray(jobj.getString(key));
    }



}


