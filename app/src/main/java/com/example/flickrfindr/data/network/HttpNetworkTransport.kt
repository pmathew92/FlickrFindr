package com.example.flickrfindr.data.network

import com.example.flickrfindr.data.util.API_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HttpNetworkTransport(private val okHttpClient: OkHttpClient) {

    val retrofit: Retrofit by lazy { createBaseRestAdapter() }

    private fun createBaseRestAdapter(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .build()
    }
}
