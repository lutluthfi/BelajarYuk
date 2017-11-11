package com.lasbon.belajaryuk_library.data.interceptor;

import com.lasbon.belajaryuk_library.data.api.ConstantAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rama on 11/11/17.
 */

public class BelajaryukInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .addHeader(ConstantAPI.HEADER_AUTH_TAG, "Bearer " + ConstantAPI.HEADER_AUTH_KEY);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
