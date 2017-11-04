package com.example.arifluthfiansyah.belajaryuk.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Arif Luthfiansyah on 25/09/2017.
 */

public class Profile extends RealmObject {

    @PrimaryKey
    private Integer id;

    private String name;

    private String handphone;

    private String address;

    private String photo;

    private String email;

    private String password;

    public Profile() {
        // Blank constructor
    }

    public Profile(Integer id, String name, String handphone, String address, String photo, String email, String password) {
        this.id = id;
        this.name = name;
        this.handphone = handphone;
        this.address = address;
        this.photo = photo;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
