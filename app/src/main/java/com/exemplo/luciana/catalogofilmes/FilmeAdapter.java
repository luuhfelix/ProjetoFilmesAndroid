package com.exemplo.luciana.catalogofilmes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exemplo.luciana.catalogofilmes.model.Filme;
import com.exemplo.luciana.catalogofilmes.services.ImageService;

import java.util.List;

/**
 * Created by Luan on 16/11/2015.
 */
public class FilmeAdapter extends BaseAdapter {

    private List<Filme> filmes;
    LayoutInflater inflater;
    private Context context;

    public FilmeAdapter(Context context, List<Filme> filmes){
        this.filmes = filmes;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return this.filmes.size();
    }

    @Override
    public Filme getItem(int i) {
        return this.filmes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Filme filme = getItem(position);

        mViewHolder.filme_nome.setText(filme.getNome());
        mViewHolder.filme_descricao.setText(filme.getGenero());

        ImageService service = new ImageService(mViewHolder.filme_img,this.context);
        service.execute(filme.getImage());

        return convertView;
    }

    private class MyViewHolder {
        TextView filme_nome, filme_descricao;
        ImageView filme_img;

        public MyViewHolder(View item) {
            filme_nome = (TextView) item.findViewById(R.id.filme_nome);
            filme_descricao = (TextView) item.findViewById(R.id.filme_descricao);
            filme_img = (ImageView) item.findViewById(R.id.filme_img);
        }
    }
}
