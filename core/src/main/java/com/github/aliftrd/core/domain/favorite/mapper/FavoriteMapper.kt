package com.github.aliftrd.core.domain.favorite.mapper

import com.github.aliftrd.core.data.favorite.model.FavoriteItem
import com.github.aliftrd.core.domain.favorite.model.Favorite

fun List<FavoriteItem>.toDomain(): List<Favorite> {
    return this.map {
        it.toDomain()
    }
}

fun FavoriteItem.toDomain(): Favorite {
    return Favorite(
        id = this.id,
        movieId = this.movieId,
        title = this.title,
        posterPath = this.posterPath,
        genre = this.genre,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        runtime = this.runtime
    )
}