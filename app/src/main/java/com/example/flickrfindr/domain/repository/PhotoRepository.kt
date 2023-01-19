package com.example.flickrfindr.domain.repository

import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.domain.util.Response

/**
 * Contract for fetching photos
 */
interface PhotoRepository {

    suspend fun fetchPhotosBySearch(searchText: String, page: Int): Response<List<Photo>>
}