package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arif Luthfiansyah on 29/10/2017.
 */

public class Jawaban implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("subject")
    @Expose
    private Subject subject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
