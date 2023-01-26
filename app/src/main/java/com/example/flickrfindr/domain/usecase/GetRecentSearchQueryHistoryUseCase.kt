package com.example.flickrfindr.domain.usecase

import com.example.flickrfindr.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetRecentSearchQueryHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend operator fun invoke(): Flow<List<String>> {
        return searchHistoryRepository.getRecentSearchQueries()
    }
}