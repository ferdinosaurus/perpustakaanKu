package com.example.ferdi.perpustakaanku.model;

public class BukuModel {


    private long id;
    private String judul;
    private String identitas;

    private String terbitan;
    private String tahun;
    private String jenisBuku;
    private int jumlahBuku;
    private String warna;
    private String rangkuman;
    private float rating;

    public BukuModel() {
    }

    public BukuModel(long id, String judul, String identitas, String terbitan, String tahun, String jenisBuku, int jumlahBuku, String warna, String rangkuman, float rating) {
        this.id = id;
        this.judul = judul;
        this.identitas = identitas;
        this.terbitan = terbitan;
        this.tahun = tahun;
        this.jenisBuku = jenisBuku;
        this.jumlahBuku = jumlahBuku;
        this.warna = warna;
        this.rangkuman = rangkuman;
        this.rating = rating;
    }

    public BukuModel(String judul, String identitas, String terbitan, String tahun, String jenisBuku, int jumlahBuku, String warna, String rangkuman, float rating) {

        this.judul = judul;
        this.identitas = identitas;
        this.terbitan = terbitan;
        this.tahun = tahun;
        this.jenisBuku = jenisBuku;
        this.jumlahBuku = jumlahBuku;
        this.warna = warna;
        this.rangkuman = rangkuman;
        this.rating = rating;
    }

    public String getTerbitan() {
        return terbitan;
    }

    public void setTerbitan(String terbitan) {
        this.terbitan = terbitan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJenisBuku() {
        return jenisBuku;
    }

    public void setJenisBuku(String jenisBuku) {
        this.jenisBuku = jenisBuku;
    }

    public int getJumlahBuku() {
        return jumlahBuku;
    }

    public void setJumlahBuku(int jumlahBuku) {
        this.jumlahBuku = jumlahBuku;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getRangkuman() {
        return rangkuman;
    }

    public void setRangkuman(String rangkuman) {
        this.rangkuman = rangkuman;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
