package com.example.movee.network

import com.example.movee.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithApiKey = chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
        val requestChain = chain.request()
            .newBuilder()
            .url(urlWithApiKey.build())
            .build()
        return chain.proceed(requestChain)
    }
}