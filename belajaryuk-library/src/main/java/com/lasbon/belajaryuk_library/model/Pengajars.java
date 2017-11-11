
package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pengajars implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Pengajar> pengajars = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Pengajar> getPengajars() {
        return pengajars;
    }

    public void setPengajars(List<Pengajar> pengajars) {
        this.pengajars = pengajars;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
