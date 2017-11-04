
package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ulasan implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ulasan")
    @Expose
    private String ulasan;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("pengajar")
    @Expose
    private Pengajar pengajar;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Pengajar getPengajar() {
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        this.pengajar = pengajar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
