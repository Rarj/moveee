package com.example.core.network

import com.example.core.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkBuilder {
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(OkHttpClient.Builder().addNetworkInterceptor(NetworkInterceptor()).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}