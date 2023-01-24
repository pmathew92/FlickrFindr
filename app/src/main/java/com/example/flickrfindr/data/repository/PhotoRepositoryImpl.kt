package com.example.flickrfindr.data.repository

import android.util.Log
import com.example.flickrfindr.data.dataSource.contract.PhotoSearchDataSource
import com.example.flickrfindr.data.util.toPhotoList
import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.util.DispatcherProvider
import com.example.flickrfindr.domain.util.Response
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
                Log.d(TAG, "Error fetching photos from web: ${throwable.stackTraceToString()}")
                Response.Failure(throwable)
            }
        }
    }

    private companion object {
        private const val TAG = "PhotoRepositoryImpl"
    }
}