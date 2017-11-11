package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Kampanye;
import com.lasbon.belajaryuk_library.model.Kampanyes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rama on 11/11/17.
 */

public interface KampanyeAPI {
    @GET("api/v1/kampanye")
    Observable<Kampanyes> getKampanyeApiCall(@Query("page") int page);

    @GET("api/v1/kampanye/{id}")
    Observable<Kampanye> getKampanyeApiCall(@Path("id") String id);
}
