package com.github.aliftrd.core.di

import com.github.aliftrd.core.di.feature.castcrew.castModule
import com.github.aliftrd.core.di.feature.favorite.favoriteModule
import com.github.aliftrd.core.di.feature.genre.genreModule
import com.github.aliftrd.core.di.feature.movie.movieModule
import org.koin.dsl.module

val coreModule = module {
    includes(
        networkModule,
        databaseModule,
        genreModule,
        movieModule,
        castModule,
        favoriteModule
    )
}