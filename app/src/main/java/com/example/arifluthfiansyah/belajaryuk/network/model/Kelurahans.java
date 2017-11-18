package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 16/11/2017.
 */

public class Kelurahans implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Kelurahan> kelurahans = null;

    public List<Kelurahan> getKelurahans() {
        return kelurahans;
    }

    public void setKelurahans(List<Kelurahan> kelurahans) {
        this.kelurahans = kelurahans;
    }
}
