package com.github.aliftrd.core.di

import androidx.room.Room
import com.github.aliftrd.core.data.lib.MoovaiDatabase
import com.github.aliftrd.core.utils.ConstVar
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MoovaiDatabase::class.java,
            ConstVar.RUNNING_DATABASE_NAME
        ).build()
    }

    single { get<MoovaiDatabase>().favoriteDao() }
}