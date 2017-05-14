package com.progdawn.amiibud;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

//https://guides.codepath.com/android/using-the-recyclerview
public class AmiiboAdapter extends RecyclerView.Adapter<AmiiboAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView seriesTextView;
        public ImageView thumbnailImageView;

        public ViewHolder(View itemView){
            super(itemView);

            nameTextView = (TextView)itemView.findViewById(R.id.amiibo_name);
            seriesTextView = (TextView)itemView.findViewById(R.id.amiibo_series);
            thumbnailImageView = (ImageView)itemView.findViewById(R.id.amiibo_thumbnail);
        }
    }

    private List<Amiibo> mAmiibos;
    private Context mContext;
    private File mPhotoFile;

    public AmiiboAdapter(Context context, List<Amiibo> amiibos){
        mAmiibos = amiibos;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public AmiiboAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View amiiboView = inflater.inflate(R.layout.amiibo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(amiiboView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AmiiboAdapter.ViewHolder holder, int position) {
        Amiibo amiibo = mAmiibos.get(position);
        TextView nameText = holder.nameTextView;
        TextView seriesText = holder.seriesTextView;
        ImageView thumbnail = holder.thumbnailImageView;
        nameText.setText(amiibo.getName());
        seriesText.setText(amiibo.getSeries());
        Picasso.with(mContext).load(new File(amiibo.getPhotoFilename())).placeholder(R.mipmap.ic_launcher).into(thumbnail);
    }

    @Override
    public int getItemCount() {
        return mAmiibos.size();
    }

    public void setAmiibos(List<Amiibo> amiibos){
        mAmiibos = amiibos;
    }

 /*   public void setImageView(Amiibo amiibo,  ImageView image){
        mPhotoFile = Collection.get(mContext).getPhotoFile(amiibo);
        if(mPhotoFile == null || !mPhotoFile.exists()){
            image.setImageDrawable(null);
        }else{
            Bitmap pictureBitmap = PictureUtils.getScaledBitmap(amiibo.getPhotoFilename().toString(), AmiiboAdapter.this);
            mPhotoView.setImageBitmap(pictureBitmap);
        }
    }*/
}
