package com.example.ferdi.perpustakaanku.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ferdi.perpustakaanku.Helper.HelperValidation;
import com.example.ferdi.perpustakaanku.R;
import com.example.ferdi.perpustakaanku.model.BukuModel;
import com.example.ferdi.perpustakaanku.sqlite.BukuDataSource;

public class FormActivity extends AppCompatActivity {

    EditText judulBukuEditText,et_identitas,ed_warna,ed_rangkuman;

    RatingBar rating;

    Spinner spinner_tahun_terbit;
    Spinner spinner_penerbit;

    TextView ed_total;
    SeekBar sb_total;

    RadioGroup rbGroup_jenis_buku;

    String jenis_buku="";

    int jumlah_buku=0;

    BukuModel bukuModel;

    BukuDataSource bukuDataSource;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initialization();
        setupData();
        seekBarListener();
    }

    public void initialization(){
        judulBukuEditText = findViewById(R.id.et_judul_buku);
        et_identitas = findViewById(R.id.et_identitas);
        ed_warna = findViewById(R.id.ed_warna);
        ed_rangkuman = findViewById(R.id.ed_rangkuman);

        rating = findViewById(R.id.rating);

        spinner_tahun_terbit = findViewById(R.id.spinner_tahun_terbit);
        spinner_penerbit = findViewById(R.id.spinner_penerbit);
        ed_total = findViewById(R.id.ed_total);
        sb_total = findViewById(R.id.sb_total);

        rbGroup_jenis_buku = findViewById(R.id.rbGroup_jenis_buku);

        bukuModel = new BukuModel();
        bukuDataSource = new BukuDataSource(this);
        id = getIntent().getLongExtra("id",0);
    }

    public void setupData(){
        if(id!=0){
            bukuDataSource.open();
            bukuModel = bukuDataSource.getById(id);
            bukuDataSource.close();

            Log.d("idUpdate", String.valueOf(bukuModel.getId()));
            judulBukuEditText.setText(bukuModel.getJudul());
            et_identitas.setText(bukuModel.getIdentitas());
            ed_warna.setText(bukuModel.getWarna());
            ed_rangkuman.setText(bukuModel.getRangkuman());
            rating.setRating(bukuModel.getRating());
            jumlah_buku = bukuModel.getJumlahBuku();
            ed_total.setText( "jumlah = "+bukuModel.getJumlahBuku());
            sb_total.setProgress(bukuModel.getJumlahBuku());
            spinner_tahun_terbit.setSelection(getSpinnerIndex(spinner_tahun_terbit,bukuModel.getTahun()));
            spinner_penerbit.setSelection(getSpinnerIndex(spinner_penerbit,bukuModel.getTerbitan()));
            if(bukuModel.getJenisBuku().equals("agama")){
                rbGroup_jenis_buku.check(R.id.rb_buku_agama);
            }
            else if(bukuModel.getJenisBuku().equals("komputer")){
                rbGroup_jenis_buku.check(R.id.rb_buku_komputer);
            }
            else if(bukuModel.getJenisBuku().equals("novel")){
                rbGroup_jenis_buku.check(R.id.rb_buku_novel);
            }
            else if(bukuModel.getJenisBuku().equals("lain - lain")){
                rbGroup_jenis_buku.check(R.id.rb_buku_lain);
            }

        }
    }
    private int getSpinnerIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public void seekBarListener(){
        sb_total.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ed_total.setText("jumlah = "+i);
                jumlah_buku= i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        jenis_buku = HelperValidation.checkGenre(rbGroup_jenis_buku.getCheckedRadioButtonId());
        if(item.getItemId()==R.id.action_save){
            if(id==0){
                insert();
            }
            else{
                update();
            }

        }
        return super.onOptionsItemSelected(item);
    }


    public void insert(){

        if(judulBukuEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "judul buku harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_identitas.getText().toString().isEmpty()){
            Toast.makeText(this, "identitas harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(spinner_tahun_terbit.getSelectedItem().toString().equals("-")){
            Toast.makeText(this, "tahun harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(jenis_buku.equals("")){
            Toast.makeText(this, "jenis buku harus dipilih", Toast.LENGTH_SHORT).show();
        }
        else if(jumlah_buku==0){
            Toast.makeText(this, "jumlah buku harus ada, minimal 1", Toast.LENGTH_SHORT).show();
        }
        else if(ed_warna.getText().toString().isEmpty()&&!ed_warna.getText().toString().startsWith("#")&&ed_warna.getText().toString().length()!=7){
            Toast.makeText(this, "kode warna harus diisi dan benar (#F0F0F0)", Toast.LENGTH_SHORT).show();
        }
        else if(HelperValidation.validate(ed_warna.getText().toString())==false){
            Toast.makeText(this, "kode warna tidak sesuai format (#FF00FF)", Toast.LENGTH_SHORT).show();
        }
        else if(ed_rangkuman.getText().toString().isEmpty()){
            Toast.makeText(this, "rangkuman harus diisi", Toast.LENGTH_SHORT).show();
        }
        else{
            bukuModel = new BukuModel(judulBukuEditText.getText().toString(),et_identitas.getText().toString(),spinner_penerbit.getSelectedItem().toString(),spinner_tahun_terbit.getSelectedItem().toString(),jenis_buku.toString(),jumlah_buku,ed_warna.getText().toString(),ed_rangkuman.getText().toString(),rating.getRating());
            Log.d("isi judul buku",bukuModel.getJudul());
            Log.d("isi identitas",bukuModel.getIdentitas());
            Log.d("isi terbitan",bukuModel.getTerbitan());
            Log.d("isi tahun",bukuModel.getTahun());
            Log.d("isi jenis buku",bukuModel.getJenisBuku());
            Log.d("isi jumlah", String.valueOf(bukuModel.getJumlahBuku()));
            Log.d("isi warna",bukuModel.getWarna());
            Log.d("isi rangkuman",bukuModel.getRangkuman());
            Log.d("isi rating", String.valueOf(bukuModel.getRating()));
            bukuDataSource.open();
            bukuDataSource.insert(bukuModel);
            bukuDataSource.close();
            Intent intent = new Intent(this,ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void update(){

        if(judulBukuEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "judul buku harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(et_identitas.getText().toString().isEmpty()){
            Toast.makeText(this, "identitas harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(spinner_tahun_terbit.getSelectedItem().toString().equals("-")){
            Toast.makeText(this, "tahun harus diisi", Toast.LENGTH_SHORT).show();
        }
        else if(jenis_buku.equals("")){
            Toast.makeText(this, "jenis buku harus dipilih", Toast.LENGTH_SHORT).show();
        }
        else if(jumlah_buku==0){
            Toast.makeText(this, "jumlah buku harus ada, minimal 1", Toast.LENGTH_SHORT).show();
        }
        else if(ed_warna.getText().toString().isEmpty()&&!ed_warna.getText().toString().startsWith("#")&&ed_warna.getText().toString().length()!=7){
            Toast.makeText(this, "kode warna harus diisi dan benar (#F0F0F0)", Toast.LENGTH_SHORT).show();
        }
        else if(HelperValidation.validate(ed_warna.getText().toString())==false){
            Toast.makeText(this, "kode warna tidak sesuai format (#FF00FF)", Toast.LENGTH_SHORT).show();
        }
        else if(ed_rangkuman.getText().toString().isEmpty()){
            Toast.makeText(this, "rangkuman harus diisi", Toast.LENGTH_SHORT).show();
        }
        else{
            bukuModel = new BukuModel(id,judulBukuEditText.getText().toString(),et_identitas.getText().toString(),spinner_penerbit.getSelectedItem().toString(),spinner_tahun_terbit.getSelectedItem().toString(),jenis_buku.toString(),jumlah_buku,ed_warna.getText().toString(),ed_rangkuman.getText().toString(),rating.getRating());
            Log.d("isi judul buku",bukuModel.getJudul());
            Log.d("isi identitas",bukuModel.getIdentitas());
            Log.d("isi terbitan",bukuModel.getTerbitan());
            Log.d("isi tahun",bukuModel.getTahun());
            Log.d("isi jenis buku",bukuModel.getJenisBuku());
            Log.d("isi jumlah", String.valueOf(bukuModel.getJumlahBuku()));
            Log.d("isi warna",bukuModel.getWarna());
            Log.d("isi rangkuman",bukuModel.getRangkuman());
            Log.d("isi rating", String.valueOf(bukuModel.getRating()));
            bukuDataSource.open();
            bukuDataSource.update(bukuModel);
            bukuDataSource.close();
            Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
