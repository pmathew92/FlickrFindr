package com.example.flickrfindr.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: Int,
    val title: String
)