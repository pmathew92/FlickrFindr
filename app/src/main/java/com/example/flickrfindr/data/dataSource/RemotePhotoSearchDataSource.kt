package com.example.flickrfindr.data.dataSource

import com.example.flickrfindr.data.dataSource.contract.PhotoSearchDataSource
import com.example.flickrfindr.data.model.PhotoSearchEntity
import com.example.flickrfindr.data.network.api.PhotoSearchApi

/**
 * Implementation of [PhotoSearchDataSource] responsible for fetching images data from
 * remote end point
 * @param [PhotoSearchApi]
 */
class RemotePhotoSearchDataSource(private val photoSearchApi: PhotoSearchApi) :
    PhotoSearchDataSource {

    override suspend fun fetchPhotosBySearchText(
        searchText: String,
        currentPage: Int
    ): PhotoSearchEntity {
        return photoSearchApi.getPhotosBySearch(searchText, currentPage)
    }
}