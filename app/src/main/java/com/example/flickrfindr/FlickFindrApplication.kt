package com.example.flickrfindr

import android.app.Application
import com.example.flickrfindr.di.commonModule
import com.example.flickrfindr.di.dataModule
import com.example.flickrfindr.di.domainModule
import com.example.flickrfindr.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class FlickFindrApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    commonModule,
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }

    }
}