package com.example.flickrfindr.data.util

import com.example.flickrfindr.data.model.PhotoSearchEntity
import com.example.flickrfindr.domain.model.Photo


/**
 * Extension method to map [PhotoSearchEntity] to [Photo] list
 */
fun PhotoSearchEntity.toPhotoList(): List<Photo> {
    return photos.photo.map { p0 ->
        Photo(
            p0.id,
            p0.title,
            THUMBNAIL_URL_FORMAT.format(p0.farm, p0.server, p0.id, p0.secret),
            IMAGE_URL_FORMAT.format(p0.farm, p0.server, p0.id, p0.secret),
        )
    }
}