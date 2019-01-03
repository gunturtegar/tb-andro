package com.guntur.aldy.cooking.models;

import com.google.gson.annotations.SerializedName;

public class ReviewResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("id_resep")
    private String id_lowongan;
    @SerializedName("konten")
    private String konten;
    @SerializedName("photo_id")
    private String photo_id;

    public ReviewResponse() {
    }

    public ReviewResponse(String id, String id_lowongan, String konten, String photo_id) {
        this.id = id;
        this.id_lowongan = id_lowongan;
        this.konten = konten;
        this.photo_id = photo_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_lowongan() {
        return id_lowongan;
    }

    public void setId_lowongan(String id_lowongan) {
        this.id_lowongan = id_lowongan;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }
}
