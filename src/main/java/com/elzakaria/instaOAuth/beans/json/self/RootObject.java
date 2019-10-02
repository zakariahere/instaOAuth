package com.elzakaria.instaOAuth.beans.json.self;

import com.google.gson.annotations.SerializedName;

public class RootObject {
    private Data data;

    @SerializedName("data")
    public Data getData() {
        return data;
    }

    @SerializedName("data")
    public void setData(Data data) {
        this.data = data;
    }
   
}