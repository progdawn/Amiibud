package com.progdawn.amiibud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

public class AmiiboAdapter extends RecyclerView.Adapter<AmiiboAdapter.ViewHolder>{
    private List<Amiibo> mAmiibos;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView seriesText;
        public ImageView amiiboThumbnail;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            nameText = (TextView)v.findViewById(R.id.amiibo_name);
            seriesText = (TextView)v.findViewById(R.id.amiibo_series);
            amiiboThumbnail = (ImageView)v.findViewById(R.id.amiibo_thumbnail);
        }
    }

    public void add(int position, Amiibo item){
        mAmiibos.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position){
        mAmiibos.remove(position);
        notifyItemRemoved(position);
    }

    public AmiiboAdapter(List<Amiibo> dataSet){
        mAmiibos = dataSet;
    }

    public AmiiboAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.amiibo_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Amiibo amiibo = mAmiibos.get(position);
        holder.nameText.setText(amiibo.getName());
        holder.seriesText.setText(amiibo.getSeries());
    }

    @Override
    public int getItemCount() {
        return mAmiibos.size();
    }
}
