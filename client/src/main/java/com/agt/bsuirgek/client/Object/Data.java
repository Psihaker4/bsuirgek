package com.agt.bsuirgek.client.Object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("params")
    @Expose
    public Params params;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("links")
    @Expose
    public List<String> links = null;
    @SerializedName("tags")
    @Expose
    public Tags tags;

}