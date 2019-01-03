package com.guntur.aldy.cooking.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultReviewResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<ReviewResponse> result = new ArrayList<ReviewResponse>();
    @SerializedName("message")
    private String message;

    public ResultReviewResponse() {
    }

    public ResultReviewResponse(String status, List<ReviewResponse> result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ReviewResponse> getResult() {
        return result;
    }

    public void setResult(List<ReviewResponse> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
