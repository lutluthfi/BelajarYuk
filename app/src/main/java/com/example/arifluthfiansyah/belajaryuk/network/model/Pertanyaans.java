package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 29/10/2017.
 */

public class Pertanyaans implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Pertanyaan> pertanyaans = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Pertanyaan> getPertanyaans() {
        return pertanyaans;
    }

    public void setPertanyaans(List<Pertanyaan> pertanyaans) {
        this.pertanyaans = pertanyaans;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
