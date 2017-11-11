package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class Kabupatens {


    @SerializedName("data")
    @Expose
    private List<Kabupaten> kabupatens = null;

    public List<Kabupaten> getKabupatens() {
        return kabupatens;
    }

    public void setKabupatens(List<Kabupaten> kabupatens) {
        this.kabupatens = kabupatens;
    }
}
