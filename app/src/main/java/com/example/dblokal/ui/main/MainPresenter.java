package com.example.dblokal.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.dblokal.entity.AppDatabase;
import com.example.dblokal.entity.DataSekolah;

public class MainPresenter implements MainContract.presenter{
    MainContract.view view;
    MainContract.hapus hapus;

    public MainPresenter(MainContract.view view){
        this.view = view;
    }

    public MainPresenter(MainContract.hapus hapus){
        this.hapus = hapus;
    }

    class EditData extends AsyncTask<Void, Void, Integer>{
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public EditData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Integer doInBackground(Void... voids){
            return database.dao().updateData(dataSekolah);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses();
        }
    }

    @Override
    public void editData(String namasekolah, String alamatsekolah, String jumlahguru, String jumlahsiswa, int id, AppDatabase database) {
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setNamasekolah(namasekolah);
        dataSekolah.setAlamatsekolah(alamatsekolah);
        dataSekolah.setJumlahguru(jumlahguru);
        dataSekolah.setJumlahsiswa(jumlahsiswa);
        dataSekolah.setId(id);
        new EditData(database, dataSekolah).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataSekolah dataSekolah;
        Context context;

        public DeleteData(AppDatabase database, DataSekolah dataSekolah){
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Void doInBackground(Void... voids){
            database.dao().deleteData(dataSekolah);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hapus.sukses();
        }
    }

    @Override
    public void deleteData(DataSekolah dataSekolah, AppDatabase database) {
        new DeleteData(database, dataSekolah).execute();
    }
}