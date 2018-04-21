package com.example.ferdi.perpustakaanku.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ferdi.perpustakaanku.adapter.BukuItemAdapter;
import com.example.ferdi.perpustakaanku.R;
import com.example.ferdi.perpustakaanku.model.BukuModel;
import com.example.ferdi.perpustakaanku.sqlite.BukuDataSource;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    BukuDataSource bukuDataSource;
    List<BukuModel> bukuModelList;

    BukuItemAdapter bukuItemAdapter;
    int check=0;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initialization();
        setupData();
        setupListener();
    }

    public void initialization(){
        bukuDataSource = new BukuDataSource(this);
        listView = findViewById(R.id.list_view);
        registerForContextMenu(listView);
    }

    public void setupData(){
        bukuDataSource.open();
        bukuModelList = bukuDataSource.getAll();
        bukuDataSource.close();

        for(int i=0;i<bukuModelList.size();i++){
            Log.d("id buku list"+i, String.valueOf(bukuModelList.get(i).getId()));
            Log.d("judul buku list"+i, String.valueOf(bukuModelList.get(i).getJudul()));
        }

        bukuItemAdapter = new BukuItemAdapter(this,0,bukuModelList);
        listView.setAdapter(bukuItemAdapter);
    }
    public void setupListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, "position"+i, Toast.LENGTH_SHORT).show();
                Log.d("checkListView","position"+i);

                Intent intent = new Intent(ListActivity.this,DetailActivity.class);
                intent.putExtra("id",bukuModelList.get(i).getId());
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Toast.makeText(this, "ini menu add", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ListActivity.this,FormActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = menuInfo.position;

        final BukuModel bukuModel =  bukuModelList.get(position);

        if(id==R.id.action_edit){

            Toast.makeText(this, "edit posisi diklik : "+bukuModel.getId(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ListActivity.this,FormActivity.class);
            intent.putExtra("id",bukuModel.getId());
            startActivity(intent);


        }
        else if(id==R.id.action_delete){
            //deleteStudent(student,position);

            new AlertDialog.Builder(this)
                    .setTitle("menghapus Data")
                    .setMessage("Yakin mau menghapus ?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //deleteStudent(student,position);
                            deleteBuku(bukuModel,position);
                        }
                    }).setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();

        }
        return super.onContextItemSelected(item);
    }

    public void deleteBuku(BukuModel bukuModel,int position){
        bukuDataSource.open();
        check = bukuDataSource.delete(bukuModel.getId());
        bukuDataSource.close();

        if(check==1){
            Toast.makeText(this, "data berhasil dihapus", Toast.LENGTH_SHORT).show();
            bukuModelList.remove(position);
            bukuItemAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this, "data gagal dihapus", Toast.LENGTH_SHORT).show();
        }
    }
}
