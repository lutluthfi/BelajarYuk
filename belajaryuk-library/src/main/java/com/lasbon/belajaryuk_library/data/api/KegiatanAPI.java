package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Kegiatan;
import com.lasbon.belajaryuk_library.model.Kegiatans;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rama on 11/11/17.
 */

public interface KegiatanAPI {
    @GET("api/v1/kegiatan")
    Observable<Kegiatans> getKegiatanApiCall(@Query("page") int page);

    @GET("api/v1/kegiatan/{id}")
    Observable<Kegiatan> getKegiatanApiCall(@Path("id") String id);
}
