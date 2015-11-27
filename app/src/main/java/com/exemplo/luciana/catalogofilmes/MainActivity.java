package com.exemplo.luciana.catalogofilmes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.exemplo.luciana.catalogofilmes.model.Filme;
import com.exemplo.luciana.catalogofilmes.services.FilmeService;

import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<Filme> filmes;
    ListView lvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDetail = (ListView) findViewById(R.id.lvCustomList);
        FilmeAdapter filmeAdapter = new FilmeAdapter(this,this.getFilmes());
        lvDetail.setAdapter(filmeAdapter);
        lvDetail.setOnItemClickListener(this);
    }


    public List<Filme> getFilmes() {
        try {
            FilmeService ws = new FilmeService();
            filmes = ws.execute().get();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return filmes;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Filme filme = filmes.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("filme", filme);
        startActivity(intent);
    }
}


