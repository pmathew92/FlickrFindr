package com.example.flickrfindr.di

import com.example.flickrfindr.domain.util.DispatcherProvider
import com.example.flickrfindr.util.DispatcherProviderImpl
import org.koin.dsl.module

/**
 * Koin module for all common classes across the application
 */
val commonModule = module {

    factory<DispatcherProvider> {
        DispatcherProviderImpl()
    }
}


