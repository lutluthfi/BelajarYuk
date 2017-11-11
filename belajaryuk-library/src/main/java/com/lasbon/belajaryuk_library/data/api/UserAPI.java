package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by rama on 11/11/17.
 */

public interface UserAPI {
    @Headers("Accept: application/json")
    @GET("api/v1/users")
    Observable<User> getUserApiCall(@Header("Authorization") String authorization);
}
