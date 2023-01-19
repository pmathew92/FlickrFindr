package com.example.flickrfindr.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoSearchEntity(
    val photos: Photos,
    val stat: String
)