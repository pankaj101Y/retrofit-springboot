package com.example.postservice.api;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClientBuilder {

    private final Converter.Factory factory=GsonConverterFactory.create();
    private final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private final String baseUrl;

    public ApiClientBuilder(String baseUrl){
        this.baseUrl=baseUrl;
    }

    public <T> T build(Class<T> tClass){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(httpClient.build())
                .build();


      return retrofit.create(tClass);
    }


}
