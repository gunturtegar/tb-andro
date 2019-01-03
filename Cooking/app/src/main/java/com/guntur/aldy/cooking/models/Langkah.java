package com.guntur.aldy.cooking.models;

import java.io.Serializable;

public class Langkah implements Serializable {
    int no;
    String judul,keterangan;

    public Langkah(int no, String judul, String keterangan) {
        this.no = no;
        this.judul = judul;
        this.keterangan = keterangan;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
