package com.guntur.aldy.cooking.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultKategoriResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<KategoriResponse> result = new ArrayList<KategoriResponse>();
    @SerializedName("message")
    private String message;
    public ResultKategoriResponse() {}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<KategoriResponse> getResult() {
        return result;
    }
    public void setResult(List<KategoriResponse> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
