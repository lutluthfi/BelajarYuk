package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Pengajars;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rama on 11/11/17.
 */

public interface PengajarAPI {
    @GET("api/v1/pengajar")
    Observable<Pengajars> getPengajarApiCall(
            @Query("lokasi") String location,
            @Query("page") int page
    );
}
