package com.pinslog.mvvmexample.util;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static RetrofitInstance getInstance() {
        return INSTANCE;
    }

    private static final RetrofitInstance INSTANCE = new RetrofitInstance();

    private final Retrofit retrofit;


    private RetrofitInstance() {


        retrofit = new Retrofit.Builder().client(new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS) // Timeout 3초
                .connectionPool(new ConnectionPool(5, 50, TimeUnit.SECONDS))
                .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();


    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
