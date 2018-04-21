package com.example.ferdi.perpustakaanku.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NAMA_TABLE ="buku";
    public static final String KOLOM_ID = "id";
    public static final String KOLOM_JUDUL="judul";
    public static final String KOLOM_IDENTITAS ="identitas";
    public static final String KOLOM_TERBITAN ="terbitan";
    public static final String KOLOM_TAHUN="tahun";
    public static final String KOLOM_JENIS ="jenis";
    public static final String KOLOM_JUMLAH= "jumlah";
    public static final String KOLOM_WARNA ="warna";
    public static final String KOLOM_RANGKUMAN ="rangkuman";
    public static final String KOLOM_RATING ="rating";

    public DatabaseHelper(Context context) {
        super(context,"perpustakaanku.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+NAMA_TABLE+"(" +
                KOLOM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                KOLOM_JUDUL+" TEXT," +
                KOLOM_IDENTITAS+" TEXT," +
                KOLOM_TERBITAN+" TEXT," +
                KOLOM_TAHUN+" TEXT," +
                KOLOM_JENIS+" TEXT," +
                KOLOM_JUMLAH+" TEXT," +
                KOLOM_WARNA+" TEXT," +
                KOLOM_RANGKUMAN+" TEXT," +
                KOLOM_RATING+" REAL)";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
