package com.example.flickrfindr.data.util

import com.example.flickrfindr.data.model.PhotoSearchDto
import com.example.flickrfindr.domain.model.Photo


/**
 * Extension method to map [PhotoSearchDto] to [Photo] list
 */
fun PhotoSearchDto.toPhotoList(): List<Photo> {
    return photos.photo.map { p0 ->
        Photo(
            p0.id,
            p0.title,
            THUMBNAIL_URL_FORMAT.format(p0.farm, p0.server, p0.id, p0.secret),
            IMAGE_URL_FORMAT.format(p0.farm, p0.server, p0.id, p0.secret),
        )
    }
}