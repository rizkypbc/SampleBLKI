package com.example.asus.sampleblki.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.sampleblki.Model.Berita;
import com.example.asus.sampleblki.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 09/05/2017.
 */

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.HolderItem> {

    private Context mContext;
    private ArrayList<Berita> mBerita;

    public BeritaAdapter(Context context, ArrayList<Berita> berita){
        this.mContext = context;
        this.mBerita = berita;
    }

    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderItem holderItem = new HolderItem(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(HolderItem holder, int position){
        Berita currentBerita = mBerita.get(position);

        holder.mImage.setImageResource(currentBerita.getmImage());
        holder.mNama.setText(currentBerita.getmNama());
        holder.mTanggal.setText(currentBerita.getmTanggal());

    }

    @Override
    public int getItemCount(){
        return mBerita.size();
    }

    public class HolderItem extends RecyclerView.ViewHolder{
        public CardView cvItem;
        public ImageView mImage;
        public TextView mNama;
        public TextView mTanggal;

        public HolderItem(View itemView){
            super(itemView);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
            mImage = (ImageView) itemView.findViewById(R.id.img_icon);
            mNama = (TextView) itemView.findViewById(R.id.tvNama);
            mTanggal = (TextView) itemView.findViewById(R.id.tvAlamat);
        }
    }
}
