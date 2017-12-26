package com.apps911.brunotrovo.n26bc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;

/**
 * Created by brunotrovo on 17/12/17.
 */

public class JsonUtils {

    private static final String UTC_DATE_TIME_FORMAT = "yyyy-MM-DD'T'HH:mm:ss";

    private static Gson gsonInstance;

    private JsonUtils() {
    }

    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .setDateFormat(UTC_DATE_TIME_FORMAT)
                    .registerTypeAdapter(Date.class,
                            (JsonDeserializer<Date>) (json, typeOfT, context) ->
                                    new Date(json.getAsJsonPrimitive().getAsLong()))
                    .create();
        }
        return gsonInstance;
    }

}