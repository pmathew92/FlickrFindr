package com.example.flickrfindr.domain.repository

import kotlinx.coroutines.flow.Flow


interface SearchHistoryRepository {

    suspend fun saveSearchQuery(searchQuery: String)

    suspend fun getRecentSearchQueries(): Flow<List<String>>
}