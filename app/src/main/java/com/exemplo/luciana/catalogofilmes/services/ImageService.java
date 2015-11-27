package com.exemplo.luciana.catalogofilmes.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Luan on 16/11/2015.
 */
public class ImageService extends AsyncTask<String, Void, Bitmap>{

        private ImageView imageView;
        private Context context;
        private ProgressDialog progressDialog;

        public ImageService(ImageView imageView,Context context){
            this.imageView = imageView;
            this.context = context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Carregando Imagem...");
        }

        @Override
        protected void onPreExecute() {
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
            imageView.setImageBitmap(result);
        }
}
