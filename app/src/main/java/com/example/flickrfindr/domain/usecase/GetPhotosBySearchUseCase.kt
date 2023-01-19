package com.example.flickrfindr.domain.usecase

import com.example.flickrfindr.domain.model.Photo
import com.example.flickrfindr.domain.repository.PhotoRepository
import com.example.flickrfindr.domain.util.Response

/**
 * Use case class for fetching photos by search
 * @param [PhotoRepository]
 */
class GetPhotosBySearchUseCase(private val photoRepository: PhotoRepository) {
    suspend operator fun invoke(searchText: String, page: Int): Response<List<Photo>> {
        return photoRepository.fetchPhotosBySearch(searchText = searchText, page = page)
    }
}