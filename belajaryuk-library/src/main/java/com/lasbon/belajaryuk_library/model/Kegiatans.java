
package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Kegiatans implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Kegiatan> kegiatans = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Kegiatan> getKegiatans() {
        return kegiatans;
    }

    public void setKegiatans(List<Kegiatan> kegiatans) {
        this.kegiatans = kegiatans;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
