package com.guntur.aldy.cooking.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by alhamdulillah on 11/2/16.
 */
public class ResultResepResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<ResepResponse> result = new ArrayList<ResepResponse>();
    @SerializedName("message")
    private String message;
    public ResultResepResponse() {}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<ResepResponse> getResult() {
        return result;
    }
    public void setResult(List<ResepResponse> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}