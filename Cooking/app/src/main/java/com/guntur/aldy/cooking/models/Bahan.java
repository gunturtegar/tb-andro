package com.guntur.aldy.cooking.models;

import java.io.Serializable;

public class Bahan implements Serializable {
    String nama;
    int jumlah;
    String satuan;

    public Bahan(String nama, int jumlah, String satuan) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = satuan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
