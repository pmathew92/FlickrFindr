package com.example.flickrfindr.domain.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ioDispatcher(): CoroutineDispatcher
    fun defaultDispatcher(): CoroutineDispatcher
    fun mainDispatcher(): CoroutineDispatcher
}