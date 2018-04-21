package com.example.ferdi.perpustakaanku.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ferdi.perpustakaanku.R;
import com.example.ferdi.perpustakaanku.model.BukuModel;

import java.util.List;

public class BukuItemAdapter extends ArrayAdapter<BukuModel> {


    public BukuItemAdapter(@NonNull Context context, int resource, @NonNull List<BukuModel> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=convertView;

        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_buku, parent, false);
        }

        BukuModel bukuModel = getItem(position);

        TextView judul = (TextView) view.findViewById(R.id.ed_item_judul);
        TextView ed_item_jenis = (TextView) view.findViewById(R.id.ed_item_jenis);
        TextView ed_item_penerbit = (TextView) view.findViewById(R.id.ed_item_penerbit);
        ImageView img_item = view.findViewById(R.id.img_item);
        RatingBar rating_item = view.findViewById(R.id.rating_item);

        judul.setText(bukuModel.getJudul());
        ed_item_jenis.setText(bukuModel.getJenisBuku());
        ed_item_penerbit.setText(bukuModel.getTerbitan());
        img_item.setBackgroundColor(Color.parseColor(bukuModel.getWarna()));
        rating_item.setRating(bukuModel.getRating());

        return view;
    }
}
