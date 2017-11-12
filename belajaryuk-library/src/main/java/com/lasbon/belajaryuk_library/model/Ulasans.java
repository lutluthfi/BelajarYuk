
package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ulasans implements Serializable{

    @SerializedName("data")
    @Expose
    private List<Ulasan> ulasans = null;

    public List<Ulasan> getUlasans() {
        return ulasans;
    }

    public void setUlasans(List<Ulasan> ulasans) {
        this.ulasans = ulasans;
    }

}
