package com.lasbon.belajaryuk_library.data.api.deserializable;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lasbon.belajaryuk_library.data.api.ConstantAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rama on 11/11/17.
 */

public abstract class BaseListDeserializer<T> implements JsonDeserializer<List<T>> {

    JsonDeserializer jsonDeserializer;
    Type type;

    public BaseListDeserializer(Type type, JsonDeserializer jsonDeserializer){
        this.jsonDeserializer = jsonDeserializer;
        this.type = type;
    }

    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("JsonDeserializer", "------BaseListDeserializer : " + type);


        final JsonObject root = json.getAsJsonObject();
        JsonArray jsonArray = root.get(ConstantAPI.JSON_TAG_ARRAY).getAsJsonArray();
        List<T> list = new ArrayList<>();
        Gson gson = new GsonBuilder().registerTypeAdapter(type, jsonDeserializer).create();
        for (int i = 0; i < jsonArray.size(); i++) {
            T t = gson.fromJson(jsonArray.get(i).getAsJsonObject(), type);
            list.add(t);
        }
        return list;
    }

}

