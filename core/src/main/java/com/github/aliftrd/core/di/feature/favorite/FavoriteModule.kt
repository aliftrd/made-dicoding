package com.github.aliftrd.core.di.feature.favorite

import com.github.aliftrd.core.data.favorite.FavoriteDataStore
import com.github.aliftrd.core.data.favorite.FavoriteRepository
import com.github.aliftrd.core.domain.favorite.FavoriteInteractor
import com.github.aliftrd.core.domain.favorite.FavoriteUseCase
import org.koin.dsl.module

val favoriteModule = module {
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
    factory<FavoriteRepository> { FavoriteDataStore(get()) }

    single { FavoriteDataStore(get()) }
    single { FavoriteInteractor(get()) }
}