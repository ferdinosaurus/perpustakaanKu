package com.example.ferdi.perpustakaanku.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferdi.perpustakaanku.R;
import com.example.ferdi.perpustakaanku.model.BukuModel;
import com.example.ferdi.perpustakaanku.sqlite.BukuDataSource;

public class DetailActivity extends AppCompatActivity {

    long id;
    ImageView img_detail;
    BukuDataSource bukuDataSource;
    BukuModel bukuModel;
    RatingBar rating_detail;

    TextView tv_detail_judul,tv_detail_isbn,tv_detail_tahun,tv_detail_penerbit,tv_detail_jenis,tv_detail_jumlah,tv_detail_rangkuman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialization();
        if(id==0){
            Toast.makeText(this, "id=0", Toast.LENGTH_SHORT).show();
        }
        else{
            setupData();
        }
    }

    public void initialization(){
        id = getIntent().getLongExtra("id",0);
        img_detail = findViewById(R.id.img_detail);
        rating_detail = findViewById(R.id.rating_detail);
        tv_detail_judul = findViewById(R.id.tv_detail_judul);
        tv_detail_isbn = findViewById(R.id.tv_detail_isbn);
        tv_detail_tahun= findViewById(R.id.tv_detail_tahun);
        tv_detail_penerbit = findViewById(R.id.tv_detail_penerbit);
        tv_detail_jenis = findViewById(R.id.tv_detail_jenis);
        tv_detail_jumlah = findViewById(R.id.tv_detail_jumlah);
        tv_detail_rangkuman = findViewById(R.id.tv_detail_rangkuman);
    }

    public void setupData(){
        bukuDataSource = new BukuDataSource(this);
        bukuDataSource.open();
        bukuModel = bukuDataSource.getById(id);
        bukuDataSource.close();
        img_detail.setBackgroundColor(Color.parseColor(bukuModel.getWarna()));
        rating_detail.setRating(bukuModel.getRating());
        tv_detail_judul.setText(bukuModel.getJudul());
        tv_detail_isbn.setText(bukuModel.getIdentitas());
        tv_detail_tahun.setText(bukuModel.getTahun());
        tv_detail_penerbit.setText(bukuModel.getTerbitan());
        tv_detail_jenis.setText(bukuModel.getJenisBuku());
        tv_detail_jumlah.setText(String.valueOf(bukuModel.getJumlahBuku()));
        tv_detail_rangkuman.setText(bukuModel.getRangkuman());
    }
}