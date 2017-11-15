package com.example.arifluthfiansyah.belajaryuk.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arif Luthfiansyah on 15/11/2017.
 */

public class ResponseSendNotification {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("recipients")
    @Expose
    private Integer recipients;
    @SerializedName("errors")
    @Expose
    private List<String> errors = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRecipients() {
        return recipients;
    }

    public void setRecipients(Integer recipients) {
        this.recipients = recipients;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
