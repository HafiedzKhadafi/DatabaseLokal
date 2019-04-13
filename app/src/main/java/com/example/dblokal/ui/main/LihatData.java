package com.example.dblokal.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.example.dblokal.R;
import com.example.dblokal.entity.AppDatabase;
import com.example.dblokal.entity.DataSekolah;
import com.example.dblokal.ui.main.holder.MainAdapter;
import java.util.List;

public class LihatData extends AppCompatActivity implements MainContract.hapus {
    private AppDatabase appDatabase;
    private MainAdapter adapter;
    private MainPresenter presenter;
    View view;
    RecyclerView recyclerView;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_data);
        presenter = new MainPresenter(this);
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        readData(appDatabase);
    }

    public void readData(AppDatabase database){
        List list;
        list = database.dao().getData();
        adapter = new MainAdapter(getApplicationContext(), list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void sukses(){
        Toast.makeText(getApplicationContext(), "Data Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatData.class));
    }

    @Override
    public void deleteData(final DataSekolah item){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }
        else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Hapus Data?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
