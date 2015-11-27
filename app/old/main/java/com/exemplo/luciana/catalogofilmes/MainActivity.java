package com.exemplo.luciana.catalogofilmes;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.exemplo.luciana.catalogofilmes.model.Filme;
import com.exemplo.luciana.catalogofilmes.services.FilmeService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private List<Filme> filmes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> filmeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.getFilmes());
        setListAdapter(filmeAdapter);
        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

    public List<String> getFilmes() {

        FilmeService ws = new FilmeService();
        List<String> itens = new ArrayList<String>();

        try {
            filmes = ws.doService();
            for (Filme f : filmes) {
                itens.add(f.getNome());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itens;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Filme filme = filmes.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("filme",filme);
        startActivity(intent);
    }
}


