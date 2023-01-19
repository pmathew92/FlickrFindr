package com.example.flickrfindr.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    val page: Int,
    val pages: String,
    val perpage: Int,
    val photo: List<Photo>,
    val total: String
)