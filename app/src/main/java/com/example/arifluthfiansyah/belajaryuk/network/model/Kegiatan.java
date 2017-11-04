package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kegiatan implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("penyelenggara")
    @Expose
    private Penyelenggara penyelenggara;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Penyelenggara getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(Penyelenggara penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

}
