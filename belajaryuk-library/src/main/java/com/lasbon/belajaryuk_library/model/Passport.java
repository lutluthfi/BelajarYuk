package com.lasbon.belajaryuk_library.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arif Luthfiansyah on 18/10/2017.
 */

public class Passport {

    @SerializedName("client_id")
    @Expose
    private Integer clientId;
    @SerializedName("client_secret")
    @Expose
    private String clientSecret;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("grant_type")
    @Expose
    private String grantType;
    @SerializedName("theNewProvider")
    @Expose
    private String theNewProvider;
    @SerializedName("player_id")
    @Expose
    private String player_id;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getTheNewProvider() {
        return theNewProvider;
    }

    public void setTheNewProvider(String theNewProvider) {
        this.theNewProvider = theNewProvider;
    }

    public String getPlayerId() {
        return player_id;
    }

    public void setPlayerId(String player_id) {
        this.player_id = player_id;
    }
}
