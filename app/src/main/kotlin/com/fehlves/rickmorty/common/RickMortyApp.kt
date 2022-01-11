package com.fehlves.rickmorty.common

import android.app.Application
import com.fehlves.rickmorty.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RickMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@RickMortyApp)
            modules(mutableListOf(repositoryModule, retrofitModule, apiModule, detailRepositoryModule, detailApiModule).apply {
                addAll(
                    viewModelModule
                )
            })
        }
    }
}