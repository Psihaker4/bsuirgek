package com.agt.bsuirgek.client.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sRetrofit = new Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass){

        HttpLoggingInterceptor loging = new HttpLoggingInterceptor();
        loging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        httpClient.addInterceptor(loging);

        Retrofit retrofit = sRetrofit
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }

}
