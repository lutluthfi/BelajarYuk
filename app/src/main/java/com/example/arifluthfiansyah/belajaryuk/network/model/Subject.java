package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arif Luthfiansyah on 16/11/2017.
 */

public class Subject implements Serializable {

    @SerializedName("data")
    @Expose
    private SubjectData subjectData;

    public SubjectData getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(SubjectData subjectData) {
        this.subjectData = subjectData;
    }
}
