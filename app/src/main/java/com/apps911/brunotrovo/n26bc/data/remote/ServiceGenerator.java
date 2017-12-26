package com.apps911.brunotrovo.n26bc.data.remote;

import com.apps911.brunotrovo.n26bc.utils.JsonUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final int TIMEOUT = 30;
    private static final String BASE_URL = "https://api.blockchain.info/";

    private static Retrofit retrofit;

    private ServiceGenerator() {
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.getGsonInstance()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY));

        return builder.build();
    }

    public static <T> T create(Class<T> clazz) {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit.create(clazz);
    }

}