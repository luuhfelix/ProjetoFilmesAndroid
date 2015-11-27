package com.exemplo.luciana.catalogofilmes.services;

import com.exemplo.luciana.catalogofilmes.model.Filme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luciana on 14/11/2015.
 */
public class FilmeService extends WebService{

    public List<Filme> doService() throws Exception {
        JSONArray response = this.getJson("https://api.myjson.com/bins/23geo", "Os Filmes mais Esperados");

        List<Filme> encontrados = new ArrayList<Filme>();

        try {

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

        } catch (JSONException e) {
            // handle exception
        }

        return encontrados;
    }

}
