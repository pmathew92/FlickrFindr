package com.example.flickrfindr.domain.usecase

import com.example.flickrfindr.domain.repository.SearchHistoryRepository

class SaveSearchQueryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend operator fun invoke(searchText: String) {
        return searchHistoryRepository.saveSearchQuery(searchText)
    }
}