package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arif Luthfiansyah on 29/10/2017.
 */

public class Pertanyaan implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pelajaran")
    @Expose
    private String pelajaran;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("terjawab")
    @Expose
    private Integer terjawab;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("jawaban")
    @Expose
    private Jawabans jawabans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
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

    public Integer getTerjawab() {
        return terjawab;
    }

    public void setTerjawab(Integer terjawab) {
        this.terjawab = terjawab;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Jawabans getJawabans() {
        return jawabans;
    }

    public void setJawabans(Jawabans jawabans) {
        this.jawabans = jawabans;
    }
}
