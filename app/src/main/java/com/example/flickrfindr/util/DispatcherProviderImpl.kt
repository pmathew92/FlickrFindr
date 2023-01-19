package com.example.flickrfindr.util

import com.example.flickrfindr.domain.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Providing an implementation of [DispatcherProvider]
 */
class DispatcherProviderImpl : DispatcherProvider {

    override fun ioDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun defaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun mainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}