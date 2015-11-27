package com.exemplo.luciana.catalogofilmes.services;

import android.os.AsyncTask;

import com.exemplo.luciana.catalogofilmes.model.Filme;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciana on 14/11/2015.
 */
public class FilmeService extends AsyncTask<String, Void, List<Filme>>{

    public List<Filme> doService(){
        List<Filme> encontrados = new ArrayList<Filme>();

        try {
            WebService ws = new WebService();
            JSONArray response = ws.getJson("https://api.myjson.com/bins/33yhd", "Os Filmes mais Esperados");

            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                Filme filme = new Filme();
                filme.setNome(obj.getString("nome"));
                filme.setDiretor(obj.getString("diretor"));
                filme.setAtores(obj.getString("atores"));
                filme.setLancamento(obj.getString("lancamento"));
                filme.setNacionalidade(obj.getString("nacionalidade"));
                filme.setGenero(obj.getString("genero"));
                filme.setImage(obj.getString("img"));
                encontrados.add(filme);
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return encontrados;
    }

    @Override
    protected List<Filme> doInBackground(String... strings) {

        return this.doService();
    }
}
