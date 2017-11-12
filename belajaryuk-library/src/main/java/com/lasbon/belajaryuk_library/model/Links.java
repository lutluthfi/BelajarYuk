package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arif Luthfiansyah on 16/09/2017.
 */

public class Links {

    @SerializedName("next")
    @Expose
    private String next;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
