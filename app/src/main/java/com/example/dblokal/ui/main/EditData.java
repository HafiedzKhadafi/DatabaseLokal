package com.example.dblokal.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.dblokal.R;
import com.example.dblokal.entity.AppDatabase;
import com.example.dblokal.entity.DataSekolah;
import com.example.dblokal.ui.main.holder.MainAdapter;

public class EditData extends AppCompatActivity implements MainContract.view{
    private AppDatabase appDatabase;
    private MainPresenter presenter;
    private MainAdapter adapter;
    private EditText et_namasekolah, et_alamatsekolah, et_jumlahguru, et_jumlahsiswa;
    private Button btnsubmit;
    private boolean edit = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

        et_namasekolah = findViewById(R.id.id_namasekolah);
        et_alamatsekolah = findViewById(R.id.id_alamatsekolah);
        et_jumlahguru = findViewById(R.id.id_jumlahguru);
        et_jumlahsiswa = findViewById(R.id.id_jumlahsiswa);
        btnsubmit = findViewById(R.id.id_btnsimpan);
        presenter = new MainPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        et_namasekolah.setText(getIntent().getStringExtra("namasekolah"));
        et_alamatsekolah.setText(getIntent().getStringExtra("alamatsekolah"));
        et_jumlahguru.setText(getIntent().getStringExtra("jumlahguru"));
        et_jumlahsiswa.setText(getIntent().getStringExtra("jumlahsiswa"));
        //private String id = getIntent().getIntExtra("id", 99);
        btnsubmit.setOnClickListener(this);
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatData.class));
    }

    @Override
    public void resetForm() {
        et_namasekolah.setText("");
        et_alamatsekolah.setText("");
        et_jumlahguru.setText("");
        et_jumlahsiswa.setText("");
        btnsubmit.setText("SUBMIT");
    }

    @Override
    public void editData(DataSekolah item) {
        et_namasekolah.setText(item.getNamasekolah());
        et_alamatsekolah.setText(item.getAlamatsekolah());
        et_jumlahguru.setText(item.getJumlahguru());
        et_jumlahsiswa.setText(item.getJumlahsiswa());
        edit = true;
        btnsubmit.setText("UBAH");
    }

    @Override
    public void onClick(View v) {
        if(v ==  btnsubmit){
            if(et_namasekolah.getText().toString().equals("") || et_alamatsekolah.getText().toString().equals("") || et_jumlahguru.getText().toString().equals("") || et_jumlahsiswa.getText().toString().equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            }
            else {
                presenter.editData(et_namasekolah.getText().toString(), et_alamatsekolah.getText().toString(), et_jumlahguru.getText().toString(), et_jumlahsiswa.getText().toString(), id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}
