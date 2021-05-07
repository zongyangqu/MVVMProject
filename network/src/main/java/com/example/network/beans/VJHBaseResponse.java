package com.example.network.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VJHBaseResponse {
    @SerializedName("reason")
    @Expose
    public String reason;
    @SerializedName("error_code")
    @Expose
    public int error_code;
}
