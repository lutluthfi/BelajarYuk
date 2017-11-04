
package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kampanye implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("penggalang")
    @Expose
    private String penggalang;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("valid")
    @Expose
    private Integer valid;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPenggalang() {
        return penggalang;
    }

    public void setPenggalang(String penggalang) {
        this.penggalang = penggalang;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
