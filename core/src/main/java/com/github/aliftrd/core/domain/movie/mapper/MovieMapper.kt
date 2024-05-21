package com.github.aliftrd.core.domain.movie.mapper

import com.github.aliftrd.core.data.movie.model.MovieItem
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.utils.ext.emptyString

fun List<MovieItem>.toDomain(): List<Movie> {
    return this.map {
        it.toDomain()
    }
}

fun MovieItem.toDomain(): Movie {
    return Movie(
        overview = this.overview ?: emptyString(),
        originalLanguage = this.originalLanguage ?: emptyString(),
        originalTitle = this.originalTitle ?: emptyString(),
        video = this.video ?: false,
        title = this.title ?: emptyString(),
        genreIds = this.genreIds ?: emptyList(),
        genre = emptyList(),
        posterPath = this.posterPath ?: emptyString(),
        backdropPath = this.backdropPath ?: emptyString(),
        releaseDate = this.releaseDate ?: emptyString(),
        popularity = this.popularity ?: 0.0,
        voteAverage = this.voteAverage ?: 0.0,
        id = this.id ?: 0,
        adult = this.adult ?: false,
        voteCount = this.voteCount ?: 0
    )
}