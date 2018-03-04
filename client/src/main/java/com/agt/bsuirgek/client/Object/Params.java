package com.agt.bsuirgek.client.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("n")
    @Expose
    public String n;
    @SerializedName("surname")
    @Expose
    public String surname;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("patronymic")
    @Expose
    public String patronymic;
    @SerializedName("data")
    @Expose
    public String data;
    @SerializedName("phone")
    @Expose
    public String phone;

}
