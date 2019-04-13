package com.example.dblokal.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "sekolah_db")
public class DataSekolah {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "namasekolah")
    private String namasekolah;
    @ColumnInfo(name = "alamatsekolah")
    private String alamatsekolah;
    @ColumnInfo(name = "jumlahguru")
    private String jumlahguru;
    @ColumnInfo(name = "jumlahsiswa")
    private String jumlahsiswa;

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNamasekolah() {
        return namasekolah;
    }

    public void setNamasekolah(String namasekolah) {
        this.namasekolah = namasekolah;
    }

    public String getAlamatsekolah() {
        return alamatsekolah;
    }

    public void setAlamatsekolah(String alamatsekolah) {
        this.alamatsekolah = alamatsekolah;
    }

    public String getJumlahguru() {
        return jumlahguru;
    }

    public void setJumlahguru(String jumlahguru) {
        this.jumlahguru = jumlahguru;
    }

    public String getJumlahsiswa() {
        return jumlahsiswa;
    }

    public void setJumlahsiswa(String jumlahsiswa) {
        this.jumlahsiswa = jumlahsiswa;
    }
}
