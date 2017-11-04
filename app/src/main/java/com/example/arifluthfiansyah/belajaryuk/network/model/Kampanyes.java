
package com.example.arifluthfiansyah.belajaryuk.network.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kampanyes implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Kampanye> kampanye = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Kampanye> getKampanye() {
        return kampanye;
    }

    public void setKampanye(List<Kampanye> kampanye) {
        this.kampanye = kampanye;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
