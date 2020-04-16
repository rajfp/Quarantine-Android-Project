package com.dehaat.dehaatassignment.rest;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRestClient {

    private static AppRestClient mInstance;
    int cacheSize = 10 * 1024 * 1024; // 10 MB
    private AppRestClientService appRestClientService;
    private static Retrofit retrofit=null;

    private AppRestClient() {
        setRestClient();
    }

    public static Retrofit getInstance() {

//        if (mInstance == null) {
//            synchronized (AppRestClient.class) {
//                if (mInstance == null)
//                    mInstance = new AppRestClient();
//            }
//        }
//        return mInstance;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/") //ToDo: enter base url
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static void setAppRestClientNull() {
        mInstance = null;
    }

    private void setRestClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/") //ToDo: enter base url
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        appRestClientService = retrofit.create(AppRestClientService.class);
    }
}
