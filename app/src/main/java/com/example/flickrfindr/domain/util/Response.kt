package com.example.flickrfindr.domain.util

/**
 * Container class to hold the result from repository layer
 */
sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Failure(val throwable: Throwable? = null) : Response<Nothing>()
}