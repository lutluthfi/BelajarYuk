package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class Provinsi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kabupaten")
    @Expose
    private Kabupatens kabupatens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Kabupatens getKabupatens() {
        return kabupatens;
    }

    public void setKabupatens(Kabupatens kabupatens) {
        this.kabupatens = kabupatens;
    }

    @Override
    public String toString() {
        return nama;
    }
}
