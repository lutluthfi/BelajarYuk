package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 29/10/2017.
 */

public class Pelajarans implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Pelajaran> pelajarans = null;

    public List<Pelajaran> getPelajarans() {
        return pelajarans;
    }

    public void setPelajarans(List<Pelajaran> pelajarans) {
        this.pelajarans = pelajarans;
    }
}
