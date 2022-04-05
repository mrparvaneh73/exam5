package com.example.exam5.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer dfsfdsfdsfdsfdfffffffmm")
            .build()

        return chain.proceed(newRequest)
    }
}