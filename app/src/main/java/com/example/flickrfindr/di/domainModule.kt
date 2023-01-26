package com.example.flickrfindr.di

import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.repository.SearchHistoryRepository
import com.example.flickrfindr.domain.usecase.GetPhotosBySearchUseCase
import com.example.flickrfindr.domain.usecase.GetRecentSearchQueryHistoryUseCase
import com.example.flickrfindr.domain.usecase.SaveSearchQueryUseCase
import org.koin.dsl.module


/**
 * Koin module for all domain layer classes
 */
val domainModule = module {

    factory {
        GetPhotosBySearchUseCase(get<PhotoRepository>())
    }

    factory {
        GetRecentSearchQueryHistoryUseCase(get<SearchHistoryRepository>())
    }

    factory {
        SaveSearchQueryUseCase(get<SearchHistoryRepository>())
    }
}