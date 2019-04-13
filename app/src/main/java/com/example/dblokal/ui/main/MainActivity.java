package com.example.dblokal.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.dblokal.R;
import com.example.dblokal.entity.AppDatabase;
import com.example.dblokal.entity.DataSekolah;

public class MainActivity extends AppCompatActivity{
    AppDatabase appDatabase;
    private Button btnsimpan, btnlihat;
    private EditText etnamasekolah, etalamatsekolah, etjumlahguru, etjumlahsiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.iniDb(getApplicationContext());

        btnsimpan = findViewById(R.id.id_btnsimpan);
        btnlihat = findViewById(R.id.id_btnlihat);
        etnamasekolah = findViewById(R.id.id_namasekolah);
        etalamatsekolah = findViewById(R.id.id_alamatsekolah);
        etjumlahguru = findViewById(R.id.id_jumlahguru);
        etjumlahsiswa = findViewById(R.id.id_jumlahsiswa);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                etnamasekolah.setText("");
                etalamatsekolah.setText("");
                etjumlahguru.setText("");
                etjumlahsiswa.setText("");
            }
        });
        btnlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LihatData.class);
                startActivity(intent);
            }
        });
    }

    public void input(){
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setNamasekolah(etnamasekolah.getText().toString());
        dataSekolah.setAlamatsekolah(etalamatsekolah.getText().toString());
        dataSekolah.setJumlahguru(etjumlahguru.getText().toString());
        dataSekolah.setJumlahsiswa(etjumlahsiswa.getText().toString());
        new InsertData(appDatabase, dataSekolah).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public InsertData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Long doInBackground(Void... voids){
            return database.dao().insertData(dataSekolah);
        }

        @Override
        protected void onPostExecute(Long aLong){
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
        }
    }
}
