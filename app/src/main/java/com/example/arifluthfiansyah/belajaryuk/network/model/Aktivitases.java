package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 16/11/2017.
 */

public class Aktivitases implements Serializable {
    @SerializedName("data")
    @Expose
    private List<Aktivitas> aktivitas = null;

    public List<Aktivitas> getAktivitases() {
        return aktivitas;
    }

    public void setAktivitases(List<Aktivitas> aktivitases) {
        this.aktivitas = aktivitas;
    }
}
