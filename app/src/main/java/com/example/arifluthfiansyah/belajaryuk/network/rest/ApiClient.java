package com.example.arifluthfiansyah.belajaryuk.network.rest;

import android.content.Context;

import com.example.arifluthfiansyah.belajaryuk.R;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arif Luthfiansyah on 16/09/2017.
 */

public class ApiClient {

    private static Retrofit mRetrofit = null;

    public static ApiHelper get(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io());
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.api_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter)
                    .build();
        }
        return mRetrofit.create(ApiHelper.class);
    }

    //TODO the response is http 500, i don't know how to fix
    public static ApiHelper getUserLogin(Context context, final String authorization) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io());
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new Interceptor() {
            @ParametersAreNonnullByDefault
            @Override
            public Response intercept( Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", authorization)
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = clientBuilder.build();
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.api_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter)
                    .build();
        }
        return mRetrofit.create(ApiHelper.class);
    }
}
