package com.example.movee.network

import com.example.movee.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkBuilder {
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(OkHttpClient.Builder().addNetworkInterceptor(NetworkInterceptor()).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}