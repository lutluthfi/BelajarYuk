package com.lasbon.belajaryuk_library.data.api;

import com.lasbon.belajaryuk_library.model.Jawaban;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by rama on 11/11/17.
 */

public interface JawabanAPI {
    //http://belajaryuk.viralio.media/api/v1/jawaban/{jawaban}
    @GET("jawaban/{id}")
    Observable<Jawaban> getJawabanById(@Path("id") int id);

    //http://belajaryuk.viralio.media/api/v1/jawaban/{jawaban}
    @PUT("jawaban/{id}")
    Observable<Jawaban> updateJawabanById(@Path("id") int id);

    //http://belajaryuk.viralio.media/api/v1/jawaban/{jawaban}
    @DELETE("jawaban/{id}")
    Observable<Jawaban> deleteJawabanById(@Path("id") int id);
}
