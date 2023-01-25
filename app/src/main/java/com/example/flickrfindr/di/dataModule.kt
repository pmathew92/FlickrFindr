package com.example.flickrfindr.di

import com.example.flickrfindr.data.dataSource.RemotePhotoSearchDataSource
import com.example.flickrfindr.data.dataSource.contract.PhotoSearchDataSource
import com.example.flickrfindr.data.network.HttpClientProvider
import com.example.flickrfindr.data.network.HttpNetworkTransport
import com.example.flickrfindr.data.network.api.PhotoSearchApi
import com.example.flickrfindr.data.repository.PhotoRepositoryImpl
import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.util.DispatcherProvider
import org.koin.dsl.module

/**
 * Koin module for all data layer classes
 */
val dataModule = module {
    single {
        val httpClientProvider = HttpClientProvider()
        HttpNetworkTransport(httpClientProvider.okHttpClient).retrofit.create(PhotoSearchApi::class.java)
    }

    single<PhotoSearchDataSource> {
        RemotePhotoSearchDataSource(get<PhotoSearchApi>())
    }
    single<PhotoRepository> {
        PhotoRepositoryImpl(get<PhotoSearchDataSource>(), get<DispatcherProvider>())
    }
}