package com.example.word.service.base;

import com.example.word.BuildConfig;
import com.example.word.service.KawaiiService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Service {
    private static final String API_URL = "http://gank.avosapps.com/api/data/%E7%A6%8F%E5%88%A9/100/";

    private static KawaiiService mService;

    public static KawaiiService create() {
        if (mService == null) {

            OkHttpClient client = new OkHttpClient();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.interceptors().add(logging);
            }

            mService = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(KawaiiService.class);
        }

        return mService;
    }
}
