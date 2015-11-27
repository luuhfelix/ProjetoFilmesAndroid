package com.exemplo.luciana.catalogofilmes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exemplo.luciana.catalogofilmes.model.Cinema;
import com.exemplo.luciana.catalogofilmes.model.Filme;
import com.exemplo.luciana.catalogofilmes.services.CinemaService;
import com.google.android.gms.maps.model.Marker;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    private ProgressDialog progressDialog;

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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando Imagem...");
        new DownloadImageTask(ImgFilme).execute(filme.getImage());
    }


    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog.show();
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            bmImage.setImageBitmap(result);
        }
    }


    public void locateCines(View v){
        Intent intent = new Intent(this, CinemasOnMapsActivity.class);
        startActivity(intent);
    }
}
