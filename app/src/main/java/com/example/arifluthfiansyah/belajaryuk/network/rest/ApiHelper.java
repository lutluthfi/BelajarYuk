package com.example.arifluthfiansyah.belajaryuk.network.rest;

import com.example.arifluthfiansyah.belajaryuk.network.model.Beritas;
import com.example.arifluthfiansyah.belajaryuk.network.model.Jawabans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kabupatens;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kampanyes;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kecamatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Kegiatans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Passport;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pelajarans;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajar;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pengajars;
import com.example.arifluthfiansyah.belajaryuk.network.model.Pertanyaans;
import com.example.arifluthfiansyah.belajaryuk.network.model.PlayerID;
import com.example.arifluthfiansyah.belajaryuk.network.model.Provinsis;
import com.example.arifluthfiansyah.belajaryuk.network.model.ResponseSendNotification;
import com.example.arifluthfiansyah.belajaryuk.network.model.Token;
import com.example.arifluthfiansyah.belajaryuk.network.model.User;
import com.example.arifluthfiansyah.belajaryuk.network.model.Users;

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

    // Routes of User
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/v1/users/updatePlayerID")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization, @Body PlayerID playerId);

    @Headers("Accept: application/json")
    @GET("api/v1/users")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization);

    @Headers("Accept: application/json")
    @GET("api/v1/users/{id}")
    Flowable<User> getUserApiCall(@Header("Authorization") String authorization, @Path("id") int id);

    // Routes of Pengajar
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("api/v1/pengajar/updatePlayerID")
    Flowable<Pengajar> getPengajarApiCall(@Header("Authorization") String authorization, @Body PlayerID playerId);

    @Headers("Accept: application/json")
    @GET("api/v1/pengajar/myprofile")
    Flowable<Pengajar> getPengajarApiCall(@Header("Authorization") String authorization);

    @Headers({"Accept: application/json", "Content-Type: application/x-www-form-urlencoded"})
    @POST("api/v1/pengajar/{id}/pesan")
    @FormUrlEncoded
    Flowable<ResponseSendNotification> postOrderPengajarApiCall(
            @Header("Authorization") String authorization, @Path("id") String id,
            @Field("pelajaran") String pelajaran,@Field("keterangan") String keterangan, @Field("sesi") int sesi
    );

    @GET("api/v1/pengajar")
    Flowable<Pengajars> getPengajarApiCall(@Query("lokasi") String location, @Query("page") int page);

    // Routes of Pertanyaan
    @GET("api/v1/pertanyaan/{pelajaran}")
    Flowable<Pertanyaans> getPertanyaanApiCall(@Path("pelajaran") String pelajaran, @Query("page") int page);

    @GET("api/v1/pertanyaan/{id}/jawaban")
    Flowable<Jawabans> getJawabanPertanyaanApiCall(@Path("id") int id);

    // Routes of Pelajaran
    @GET("api/v1/pelajaran")
    Flowable<Pelajarans> getPelajaranApiCall();

    // Routes of Berita
    @GET("api/v1/berita")
    Flowable<Beritas> getBeritaApiCall(@Query("page") int page);

    // Routes of Kegiatan
    @GET("api/v1/kegiatan")
    Flowable<Kegiatans> getKegiatanApiCall(@Query("page") int page);

    // Routes of Kampanye
    @GET("api/v1/kampanye")
    Flowable<Kampanyes> getKampanyeApiCall(@Query("page") int page);

    // Routes of Region
    @GET("api/v1/provinsi")
    Flowable<Provinsis> getProvinsiApiCall();

    @GET("api/v1/kabupaten")
    Flowable<Kabupatens> getKabupatenApiCall();

    @GET("api/v1/kecamatan")
    Flowable<Kecamatans> getKecamatanApiCall();
}
