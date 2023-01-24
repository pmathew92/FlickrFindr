package com.example.flickrfindr.data.dataSource.contract

import com.example.flickrfindr.data.model.PhotoSearchDto

/**
 * contract for fetching images
 */
interface PhotoSearchDataSource {
    suspend fun fetchPhotosBySearchText(
        searchText: String,
        currentPage: Int
    ): PhotoSearchDto
}