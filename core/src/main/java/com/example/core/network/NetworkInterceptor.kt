package com.example.core.network

import com.example.core.Secret
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithApiKey = chain.request().url().newBuilder().addQueryParameter("api_key", Secret.apiKey())
        val requestChain = chain.request()
            .newBuilder()
            .url(urlWithApiKey.build())
            .build()
        return chain.proceed(requestChain)
    }
}