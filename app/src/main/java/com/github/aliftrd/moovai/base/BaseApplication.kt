package com.github.aliftrd.moovai.base

import android.app.Application
import com.github.aliftrd.core.di.coreModule
import com.github.aliftrd.detail.di.detailModule
import com.github.aliftrd.home.di.homeModule
import com.github.aliftrd.search.di.searchModule
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
                coreModule,
                homeModule,
                searchModule,
                detailModule
            )
        }
    }
}