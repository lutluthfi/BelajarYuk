package com.example.arifluthfiansyah.belajaryuk.network.rest;

import com.example.arifluthfiansyah.belajaryuk.network.model.Beritas;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawabans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kabupatens;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajarans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajars;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Provinsis;
import com.example.arifluthfiansyah.belajaryuk.network.model.ResponseSendNotification;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Arif Luthfiansyah on 16/09/2017.
 */

//TODO If this app is done, change header usage
public interface ApiHelper {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("oauth/token")
    Flowable<Token> getTokenApiCall(@Body Passport passport);

    @Headers("Accept: application/json")
    @POST("api/v1/user")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization, @Field("player_id") String playerId);

    @Headers("Accept: application/json")
    @POST("api/v1/user")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization);

    @GET("api/v1/pengajar")
    Flowable<Pengajars> getPengajarApiCall(@Query("lokasi") String location, @Query("page") int page);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/v1/pengajar/{id}/pesan")
    @FormUrlEncoded
    Flowable<ResponseSendNotification> postPesanPengajarApiCall(
            @Header("Authorization") String authorization,
            @Path("id") String id, @Field("keterangan") String keterangan, @Field("sesi") int sesi
    );

    @GET("api/v1/pertanyaan/{pelajaran}")
    Flowable<Pertanyaans> getPertanyaanApiCall(@Path("pelajaran") String pelajaran, @Query("page") int page);

    @GET("api/v1/pertanyaan/{id}/jawaban")
    Flowable<Jawabans> getJawabanPertanyaanApiCall(@Path("id") int id);

    @GET("api/v1/pelajaran")
    Flowable<Pelajarans> getPelajaranApiCall();

    @GET("api/v1/berita")
    Flowable<Beritas> getBeritaApiCall(@Query("page") int page);

    @GET("api/v1/kegiatan")
    Flowable<Kegiatans> getKegiatanApiCall(@Query("page") int page);

    @GET("api/v1/kampanye")
    Flowable<Kampanyes> getKampanyeApiCall(@Query("page") int page);

    @GET("api/v1/provinsi")
    Flowable<Provinsis> getProvinsiApiCall();

    @GET("api/v1/kabupaten")
    Flowable<Kabupatens> getKabupatenApiCall();
}
