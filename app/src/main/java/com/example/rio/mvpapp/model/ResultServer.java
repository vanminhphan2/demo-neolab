package com.example.rio.mvpapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultServer implements Serializable {

    @SerializedName("status")
    private boolean status;
    @SerializedName("data")
    private User data;
    @SerializedName("message")
    private String message;

    public ResultServer() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultServer{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
