package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class Provinsis {

    @SerializedName("data")
    @Expose
    private List<Provinsi> provinsis = null;

    public List<Provinsi> getProvinsis() {
        return provinsis;
    }

    public void setProvinsis(List<Provinsi> provinsis) {
        this.provinsis = provinsis;
    }
}
