package com.example.flickrfindr.data.network

import com.example.flickrfindr.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

class HttpClientProvider {

    val okHttpClient: OkHttpClient by lazy { createOkHttpClient() }

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(NetworkInterceptor())
            .connectTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS).build()

    companion object {
        private const val TIMEOUT_VALUE_SECONDS = 10L
    }
}

class NetworkInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl =
            chain.request().url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
        val request = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(request)
    }
}