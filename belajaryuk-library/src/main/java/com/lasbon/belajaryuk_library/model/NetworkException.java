package com.lasbon.belajaryuk_library.model;

/**
 * Created by rama on 11/11/17.
 */

public class NetworkException extends RuntimeException {

    private int responseCode = -1;


    public NetworkException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}

