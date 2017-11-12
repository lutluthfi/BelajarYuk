package com.lasbon.belajaryuk_library.data;

import com.google.gson.Gson;
import com.lasbon.belajaryuk_library.data.interceptor.BelajaryukInterceptor;
import com.lasbon.belajaryuk_library.data.interceptor.ResponseErrorInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anthonyle on 9/7/17.
 */

public class NetworkModule {


    public static Retrofit createRetrofit(String baseUrl, Gson gson){

        OkHttpClient okHttpClient = createHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;

    }

    public static OkHttpClient createHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.addInterceptor(logging);
        builder.addInterceptor(new BelajaryukInterceptor());
        builder.addInterceptor(new ResponseErrorInterceptor());
        builder.addInterceptor(loggingInterceptor);
        OkHttpClient client = builder.build();
        return client;
    }


}
