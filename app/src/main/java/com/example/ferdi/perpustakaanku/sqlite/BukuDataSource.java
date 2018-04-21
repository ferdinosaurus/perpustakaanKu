package com.example.ferdi.perpustakaanku.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ferdi.perpustakaanku.model.BukuModel;

import java.util.ArrayList;
import java.util.List;

public class BukuDataSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BukuDataSource(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public void insert(BukuModel bukuModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.KOLOM_JUDUL,bukuModel.getJudul());
        contentValues.put(DatabaseHelper.KOLOM_IDENTITAS,bukuModel.getIdentitas());
        contentValues.put(DatabaseHelper.KOLOM_TERBITAN,bukuModel.getTerbitan());
        contentValues.put(DatabaseHelper.KOLOM_TAHUN,bukuModel.getTahun());
        contentValues.put(DatabaseHelper.KOLOM_JENIS,bukuModel.getJenisBuku());
        contentValues.put(DatabaseHelper.KOLOM_JUMLAH,bukuModel.getJumlahBuku());
        contentValues.put(DatabaseHelper.KOLOM_WARNA,bukuModel.getWarna());
        contentValues.put(DatabaseHelper.KOLOM_RANGKUMAN,bukuModel.getRangkuman());
        contentValues.put(DatabaseHelper.KOLOM_RATING,bukuModel.getRating());

        database.insert(DatabaseHelper.NAMA_TABLE,null,contentValues);
    }

    public void update(BukuModel bukuModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.KOLOM_JUDUL,bukuModel.getJudul());
        contentValues.put(DatabaseHelper.KOLOM_IDENTITAS,bukuModel.getIdentitas());
        contentValues.put(DatabaseHelper.KOLOM_TERBITAN,bukuModel.getTerbitan());
        contentValues.put(DatabaseHelper.KOLOM_TAHUN,bukuModel.getTahun());
        contentValues.put(DatabaseHelper.KOLOM_JENIS,bukuModel.getJenisBuku());
        contentValues.put(DatabaseHelper.KOLOM_JUMLAH,bukuModel.getJumlahBuku());
        contentValues.put(DatabaseHelper.KOLOM_WARNA,bukuModel.getWarna());
        contentValues.put(DatabaseHelper.KOLOM_RANGKUMAN,bukuModel.getRangkuman());
        contentValues.put(DatabaseHelper.KOLOM_RATING,bukuModel.getRating());

        int check = database.update(DatabaseHelper.NAMA_TABLE,contentValues,DatabaseHelper.KOLOM_ID+" = "+bukuModel.getId(),null);

        Log.d("checkUpdate", String.valueOf(check));
    }

    public BukuModel fetchRow(Cursor cursor){
        BukuModel bukuModel = new BukuModel(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8),cursor.getLong(9));

        return bukuModel;
    }

    public List<BukuModel> getAll(){
        String query = "SELECT * FROM "+DatabaseHelper.NAMA_TABLE;
        Cursor cursor =database.rawQuery(query,null);
        List<BukuModel> bukuModelList = new ArrayList<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            BukuModel bukuModel = fetchRow(cursor);
            bukuModelList.add(bukuModel);
            cursor.moveToNext();
        }

        return bukuModelList;
    }

    public BukuModel getById(long id){
        String query = "SELECT * FROM "+DatabaseHelper.NAMA_TABLE+" WHERE "+DatabaseHelper.KOLOM_ID+" = "+id;
        Cursor cursor =database.rawQuery(query,null);

        cursor.moveToFirst();
        BukuModel bukuModel = fetchRow(cursor);

        return bukuModel;
    }

    public int delete(long id){

        int check = database.delete(DatabaseHelper.NAMA_TABLE,DatabaseHelper.KOLOM_ID+" = "+id,null);

        return check;
    }
}
