package com.lasbon.belajaryuk_library.data.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lasbon.belajaryuk_library.data.IBeritaInteractor;
import com.lasbon.belajaryuk_library.data.NetworkModule;
import com.lasbon.belajaryuk_library.data.api.BeritaAPI;
import com.lasbon.belajaryuk_library.data.api.ConstantAPI;
import com.lasbon.belajaryuk_library.data.api.deserializable.BaseListDeserializer;
import com.lasbon.belajaryuk_library.data.api.deserializable.BeritaDeserializer;
import com.lasbon.belajaryuk_library.model.Berita;
import com.lasbon.belajaryuk_library.model.Beritas;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by rama on 11/11/17.
 */

public class BeritaInteractor implements IBeritaInteractor {
    private final Retrofit retrofit;
    private final BeritaAPI beritaAPI;

    public BeritaInteractor(Retrofit retrofit, BeritaAPI assignmentAPI) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Beritas>(){}.getType() , new BaseListDeserializer(Berita.class, new BeritaDeserializer()) {})
                .registerTypeAdapter(new TypeToken<Berita>(){}.getType(), new BeritaDeserializer())
                .create();
        this.retrofit = NetworkModule.createRetrofit(ConstantAPI.BASE_URL, gson);
        this.beritaAPI = retrofit.create(BeritaAPI.class);
    }

    @Override
    public Observable<Beritas> getAllBerita() {
        return null;
    }

    @Override
    public Observable<Berita> getBeritaById(int id) {
        return null;
    }
}
