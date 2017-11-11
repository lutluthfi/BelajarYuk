package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Berita;
import com.lasbon.belajaryuk_library.model.Beritas;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rama on 11/11/17.
 */

public interface BeritaAPI {
    //http://belajaryuk.viralio.media/api/v1/berita
    @GET("berita")
    Observable<Beritas> getAllBerita();

    @GET("api/v1/berita/{id}")
    Observable<Berita> getBeritaApiCall(@Path("id") String id);
}
