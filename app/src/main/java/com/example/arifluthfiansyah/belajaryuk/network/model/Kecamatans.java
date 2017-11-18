package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class Kecamatans {

    @SerializedName("data")
    @Expose
    private List<Kecamatan> kecamatans = null;

    public List<Kecamatan> getKecamatans() {
        return kecamatans;
    }

    public void setKecamatans(List<Kecamatan> kecamatans) {
        this.kecamatans = kecamatans;
    }
}
