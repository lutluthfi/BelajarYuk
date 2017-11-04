
package com.example.arifluthfiansyah.belajaryuk.network.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pengajar implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("pendidikan_terakhir")
    @Expose
    private String pendidikanTerakhir;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("registered")
    @Expose
    private String registered;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("aktif")
    @Expose
    private Integer aktif;
    @SerializedName("ulasan")
    @Expose
    private Ulasans ulasans;
    @SerializedName("pelajaran")
    @Expose
    private Pelajarans pelajarans;
    @SerializedName("jawaban")
    @Expose
    private Jawabans jawabans;

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Integer getAktif() {
        return aktif;
    }

    public void setAktif(Integer aktif) {
        this.aktif = aktif;
    }

    public Ulasans getUlasans() {
        return ulasans;
    }

    public void setUlasans(Ulasans ulasans) {
        this.ulasans = ulasans;
    }

    public Pelajarans getPelajarans() {
        return pelajarans;
    }

    public void setPelajarans(Pelajarans pelajarans) {
        this.pelajarans = pelajarans;
    }

    public Jawabans getJawabans() {
        return jawabans;
    }

    public void setJawabans(Jawabans jawabans) {
        this.jawabans = jawabans;
    }

}
