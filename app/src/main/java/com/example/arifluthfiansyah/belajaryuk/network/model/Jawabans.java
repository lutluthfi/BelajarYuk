package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Arif Luthfiansyah on 29/10/2017.
 */

public class Jawabans implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Jawaban> jawabans = null;

    public List<Jawaban> getJawabans() {
        return jawabans;
    }

    public void setJawabans(List<Jawaban> jawabans) {
        this.jawabans = jawabans;
    }
}
