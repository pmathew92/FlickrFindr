package com.example.flickrfindr.data.repository

import com.example.flickrfindr.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SearchHistoryRepositoryImpl : SearchHistoryRepository {
    private val history = LinkedHashMap<String, String>()
    private val searchQueryList = MutableStateFlow<List<String>>(emptyList())

    override suspend fun saveSearchQuery(searchQuery: String) {
        if (!history.containsKey(searchQuery)) {
            history[searchQuery] = searchQuery
            if (history.size > MAX_SIZE) {
                val eldest = history.entries.iterator().next()
                history.remove(eldest.key)
            }
            searchQueryList.emit(history.values.toList())
        }
    }

    override suspend fun getRecentSearchQueries(): Flow<List<String>> = searchQueryList

    private companion object {
        private const val MAX_SIZE = 10
    }
}