package com.exemplo.luciana.catalogofilmes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exemplo.luciana.catalogofilmes.model.Filme;
import com.exemplo.luciana.catalogofilmes.services.ImageService;

/**
 * Created by Luciana on 27/09/2015.
 */
public class DetailsActivity extends Activity {


    private TextView TextNomeFilme;
    private TextView TextDiretorFilme;
    private TextView TextLancamentoFilme;
    private TextView TextAtoresFilme;
    private TextView TextGeneroFilme;
    private TextView TextNacionalidadeFilme;
    private ImageView ImgFilme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_activity);
        Intent intent = getIntent();

        Filme filme = (Filme) intent.getSerializableExtra("filme");

        TextNomeFilme = (TextView) findViewById(R.id.nome_filme);
        TextDiretorFilme = (TextView) findViewById(R.id.diretor_filme);
        TextLancamentoFilme = (TextView) findViewById(R.id.lancamento_filme);
        TextAtoresFilme = (TextView) findViewById(R.id.atores_filme);
        TextGeneroFilme = (TextView) findViewById(R.id.genero_filme);
        TextNacionalidadeFilme = (TextView) findViewById(R.id.nacionalidade_filme);

        ImgFilme = (ImageView)findViewById(R.id.img_filme);

        TextNomeFilme.setText(filme.getNome());
        TextDiretorFilme.setText(filme.getDiretor());
        TextLancamentoFilme.setText(filme.getLancamento());
        TextAtoresFilme.setText(filme.getAtores());
        TextGeneroFilme.setText(filme.getGenero());
        TextNacionalidadeFilme.setText(filme.getNacionalidade());
        new ImageService(ImgFilme,this).execute(filme.getImage());
    }


    public void locateCines(View v){
        Intent intent = new Intent(this, CinemasOnMapsActivity.class);
        startActivity(intent);
    }
}
