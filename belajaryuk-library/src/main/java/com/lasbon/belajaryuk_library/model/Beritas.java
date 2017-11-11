package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arif Luthfiansyah on 26/09/2017.
 */

public class Beritas implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Berita> berita = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Berita> getBeritas() {
        return berita;
    }

    public void setBeritas(List<Berita> berita) {
        this.berita = berita;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
