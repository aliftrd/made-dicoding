package com.github.aliftrd.core.di.feature.genre

import com.github.aliftrd.core.data.genre.GenreDataStore
import com.github.aliftrd.core.data.genre.GenreRepository
import com.github.aliftrd.core.domain.genre.GenreInteractor
import com.github.aliftrd.core.domain.genre.GenreUseCase
import org.koin.dsl.module

val genreModule = module {
    factory<GenreUseCase> { GenreInteractor(get()) }
    factory<GenreRepository> { GenreDataStore(get()) }

    single { GenreDataStore(get()) }
    single { GenreInteractor(get()) }
}