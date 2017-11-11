package com.lasbon.belajaryuk_library.data.interceptor;

import com.lasbon.belajaryuk_library.model.NetworkException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by rama on 11/11/17.
 */

public class ResponseErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response;
        try {
            response = chain.proceed(chain.request());
        } catch (SocketTimeoutException | UnknownHostException e) {
            throw new RuntimeException("No Connection");
        }

        if(!response.isSuccessful()){
            String message = " Please check your internet connection.";

            if (response.code() >= 400 && response.code() < 500) {
                if (response.body() != null) {
                    ResponseBody responseBody = response.body();
                    if (responseBody.contentLength() < Integer.MAX_VALUE) {
                        message = parseResponse(responseBody.string());
                    }
                }
                throw new NetworkException(message, response.code());

            } else if (response.code() >= 500 && response.code() < 600) {
                if (response.body() != null) {
                    ResponseBody responseBody = response.body();
                    if(responseBody.contentLength() < Integer.MAX_VALUE){
                        message = parseResponse(responseBody.string());
                    }
                }
                throw new NetworkException(message, response.code());

            }
        }

        return response;
    }

    public String parseResponse(String jsonString){
        String errorMessage = "Unknown";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if(jsonObject.has("message")){
                errorMessage = jsonObject.getString("message");
            }

            if(jsonObject.has("data")){
                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                if(jsonObjectData.has("errors")){
                    StringBuilder sb = new StringBuilder();
                    JSONArray jsonArrayErrors = jsonObjectData.getJSONArray("errors");
                    for(int i = 0; i < jsonArrayErrors.length(); i++){
                        JSONObject error = jsonArrayErrors.getJSONObject(i);
                        String message = error.has("message") ? error.getString("message"): "";
                        if(message.length() > 0){
                            sb.append(i == 0 ? "" : "\n").append(message);
                        }
                    }
                    errorMessage = sb.toString();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "An error occurred while processing your request. Please check your internet connection.";
        }
        return errorMessage;
    }

}

