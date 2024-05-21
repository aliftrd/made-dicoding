package com.github.aliftrd.core.di.feature.movie

import com.github.aliftrd.core.data.movie.MovieDataStore
import com.github.aliftrd.core.data.movie.MovieRepository
import com.github.aliftrd.core.domain.movie.MovieInteractor
import com.github.aliftrd.core.domain.movie.MovieUseCase
import org.koin.dsl.module

val movieModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<MovieRepository> { MovieDataStore(get(), get()) }

    single { MovieDataStore(get(), get()) }
    single { MovieInteractor(get()) }
}