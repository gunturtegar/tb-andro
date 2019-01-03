package com.guntur.aldy.cooking.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResepResponse implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("judul")
    private String judul;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("bahan")
    private String bahan;
    @SerializedName("langkah")
    private String langkah;
    @SerializedName("image")
    private String image;

    public ResepResponse() {
    }

    public ResepResponse(String id, String judul, String deskripsi, String bahan, String langkah, String image) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.bahan = bahan;
        this.langkah = langkah;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLangkah() {
        return langkah;
    }

    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
