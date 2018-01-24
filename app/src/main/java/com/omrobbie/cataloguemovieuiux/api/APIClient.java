/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 10/9/17 10:55 PM.
 */

package com.omrobbie.cataloguemovieuiux.api;

import com.omrobbie.cataloguemovieuiux.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omrobbie on 09/10/2017.
 */

public class APIClient {

    private APICall apiCall;

    public APIClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY)
                                .build();

                        original = original.newBuilder()
                                .url(httpUrl)
                                .build();

                        return chain.proceed(original);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(APICall.class);
    }

    public APICall getService() {
        return apiCall;
    }
}