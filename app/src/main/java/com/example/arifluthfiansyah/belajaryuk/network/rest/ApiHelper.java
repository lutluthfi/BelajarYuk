package com.example.arifluthfiansyah.belajaryuk.network.rest;

import com.example.arifluthfiansyah.belajaryuk.network.model.Beritas;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawabans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kabupatens;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajars;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Provinsis;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Arif Luthfiansyah on 16/09/2017.
 */

public interface ApiHelper {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("oauth/token")
    Flowable<Token> getTokenApiCall(@Body Passport passport);

    @Headers("Accept: application/json")
    @GET("api/v1/users")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization);

    @GET("api/v1/pengajar")
    Flowable<Pengajars> getPengajarApiCall(
            @Query("lokasi") String location,
            @Query("page") int page
    );

    @GET("api/v1/pertanyaan")
    Flowable<Pertanyaans> getPertanyaanApiCall(@Query("page") int page);

    @GET("api/v1/pertanyaan")
    Flowable<Pertanyaans> getPertanyaanApiCall(@Path("pelajaran") String pelajaran);

    @GET("api/v1/jawaban")
    Flowable<Jawabans> getJawabanApiCall(@Path("id") String id);

    @GET("api/v1/berita")
    Flowable<Beritas> getBeritaApiCall(@Query("page") int page);

    @GET("api/v1/berita/{id}")
    Flowable<Beritas> getBeritaApiCall(@Path("id") String id);

    @GET("api/v1/kegiatan")
    Flowable<Kegiatans> getKegiatanApiCall(@Query("page") int page);

    @GET("api/v1/kegiatan/{id}")
    Flowable<Kegiatans> getKegiatanApiCall(@Path("id") String id);

    @GET("api/v1/kampanye")
    Flowable<Kampanyes> getKampanyeApiCall(@Query("page") int page);

    @GET("api/v1/kampanye/{id}")
    Flowable<Kampanyes> getKampanyeApiCall(@Path("id") String id);

    @GET("api/v1/provinsi")
    Flowable<Provinsis> getProvinsiApiCall();

    @GET("api/v1/kabupaten")
    Flowable<Kabupatens> getKabupatenApiCall();
}
