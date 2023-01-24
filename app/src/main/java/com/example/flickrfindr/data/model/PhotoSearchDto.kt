package com.example.flickrfindr.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoSearchDto(
    val photos: Photos,
    val stat: String
)