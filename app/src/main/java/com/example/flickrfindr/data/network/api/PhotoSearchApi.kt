package com.example.flickrfindr.data.network.api

import com.example.flickrfindr.data.model.PhotoSearchEntity
import com.example.flickrfindr.data.util.PER_PAGE_RESULT
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoSearchApi {

    @GET("?method=flickr.photos.search&nojsoncallback=1&format=json")
    suspend fun getPhotosBySearch(
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PER_PAGE_RESULT
    ): PhotoSearchEntity
}