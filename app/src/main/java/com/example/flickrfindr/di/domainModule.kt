package com.example.flickrfindr.di

import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.usecase.GetPhotosBySearchUseCase
import org.koin.dsl.module


/**
 * Koin module for all domain layer classes
 */
val domainModule = module {

    factory {
        GetPhotosBySearchUseCase(get<PhotoRepository>())
    }
}