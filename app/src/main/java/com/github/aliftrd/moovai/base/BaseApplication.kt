package com.github.aliftrd.moovai.base

import android.app.Application
import com.github.aliftrd.core.di.feature.genre.genreModule
import com.github.aliftrd.core.di.feature.movie.movieModule
import com.github.aliftrd.core.di.networkModule
import com.github.aliftrd.moovai.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    genreModule,
                    movieModule,
                ),
            )
        }
    }
}