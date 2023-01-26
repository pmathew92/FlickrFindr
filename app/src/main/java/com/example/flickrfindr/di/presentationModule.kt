package com.example.flickrfindr.di

import com.example.flickrfindr.domain.usecase.GetPhotosBySearchUseCase
import com.example.flickrfindr.domain.usecase.GetRecentSearchQueryHistoryUseCase
import com.example.flickrfindr.domain.usecase.SaveSearchQueryUseCase
import com.example.flickrfindr.domain.util.DispatcherProvider
import com.example.flickrfindr.presentation.viewmodel.SearchPhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Koin module for all presentation layer classes
 */
val presentationModule = module {

    viewModel {
        SearchPhotosViewModel(
            get<GetPhotosBySearchUseCase>(),
            get<SaveSearchQueryUseCase>(),
            get<GetRecentSearchQueryHistoryUseCase>(),
            get<DispatcherProvider>()
        )
    }
}