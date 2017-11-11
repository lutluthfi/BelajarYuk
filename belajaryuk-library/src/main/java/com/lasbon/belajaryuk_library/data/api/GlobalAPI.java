package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Passport;
import com.lasbon.belajaryuk_library.model.Token;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by rama on 11/11/17.
 */

public interface GlobalAPI {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("oauth/token")
    Observable<Token> getTokenApiCall(@Body Passport passport);
}
