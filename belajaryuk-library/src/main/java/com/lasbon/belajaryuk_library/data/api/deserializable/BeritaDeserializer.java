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
import com.lasbon.belajaryuk_library.model.Berita;

import java.lang.reflect.Type;

/**
 * Created by rama on 11/11/17.
 */

public class BeritaDeserializer implements JsonDeserializer<Berita> {

    @Override
    public Berita deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("JsonDeserializer", "------BeritaDeserializer");

        if(json == null || json.isJsonNull()){
            return null;
        }
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Song.class, new SongDeserializer())
//                .registerTypeAdapter(Classroom.class, new ClassroomDeserializer())
//                .registerTypeAdapter(Tip.class, new TipDeserializer())
//                .registerTypeAdapter(Wurrly.class, new WurrlyDeserializer())
//                .create();

        final JsonObject root = json.getAsJsonObject();
        Berita.Builder builder = new Berita.Builder();
        builder
                .id(root.has(ConstantAPI.JSON_TAG_ID) ? root.get(ConstantAPI.JSON_TAG_ID).getAsLong() : -1)
                .title(root.has(ConstantAPI.JSON_TAG_TITLE) ? root.get(ConstantAPI.JSON_TAG_TITLE).getAsString() : "")
                .description(root.has(ConstantAPI.JSON_TAG_DESCRIPTION) ? root.get(ConstantAPI.JSON_TAG_DESCRIPTION).getAsString() : "")
                .dueDate(root.has(ConstantAPI.JSON_TAG_DUE_DATE) ? root.get(ConstantAPI.JSON_TAG_DUE_DATE).getAsLong() : -1)
                .gradingSystem(root.has(ConstantAPI.JSON_TAG_GRADING_SYSTEM) ? root.get(ConstantAPI.JSON_TAG_GRADING_SYSTEM).getAsString() : "")
                .useSetList(root.has(ConstantAPI.JSON_TAG_USE_SETLIST) ? root.get(ConstantAPI.JSON_TAG_USE_SETLIST).getAsBoolean() : true);


        Berita ass = builder.create();
        System.out.println("A:" + ass.toString());
        return ass;
    }
}

