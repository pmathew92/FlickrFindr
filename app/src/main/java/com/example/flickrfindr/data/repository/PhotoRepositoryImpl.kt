package com.example.flickrfindr.data.repository

import com.example.flickrfindr.data.dataSource.contract.PhotoSearchDataSource
import com.example.flickrfindr.data.util.toPhotoList
import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.util.Response
import com.example.flickrfindr.domain.util.DispatcherProvider
import kotlinx.coroutines.withContext

/**
 * Implementation of [PhotoRepository] to fetch image data
 * @param [PhotoSearchDataSource]
 * @param [DispatcherProvider]
 */
class PhotoRepositoryImpl(
    private val dataSource: PhotoSearchDataSource,
    private val dispatcherProvider: DispatcherProvider
) : PhotoRepository {

    override suspend fun fetchPhotosBySearch(searchText: String, page: Int): Response<List<Photo>> {
        return withContext(dispatcherProvider.ioDispatcher()) {
            try {
                val result = dataSource.fetchPhotosBySearchText(searchText, page)
                    .toPhotoList()
                Response.Success(result)
            } catch (throwable: Throwable) {
                Response.Failure(throwable)
            }
        }
    }
}