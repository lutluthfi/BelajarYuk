
package com.example.arifluthfiansyah.belajaryuk.network.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
