package com.guntur.aldy.cooking.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Resep implements Serializable {
    String judul;
    String deskripsi;
    String bahan;
    ArrayList<Langkah> langkah;
    int foto;

    public Resep(String judul, String deskripsi, String bahan, ArrayList<Langkah> langkah, int foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.bahan = bahan;
        this.langkah = langkah;
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public ArrayList<Langkah> getLangkah() {
        return langkah;
    }

    public void setLangkah(ArrayList<Langkah> langkah) {
        this.langkah = langkah;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
