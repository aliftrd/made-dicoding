package com.github.aliftrd.core.domain.movie.mapper

import com.github.aliftrd.core.data.movie.model.MovieDetailResponse
import com.github.aliftrd.core.data.movie.model.MovieItem
import com.github.aliftrd.core.domain.genre.mapper.toDomain
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.domain.movie.model.MovieDetail
import com.github.aliftrd.core.utils.ext.emptyString

fun List<MovieItem>.toDomain(): List<Movie> {
    return this.map {
        it.toDomain()
    }
}

fun MovieItem.toDomain(): Movie {
    return Movie(
        overview = this.overview,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        video = this.video,
        title = this.title,
        genreIds = this.genreIds,
        genre = emptyList(),
        posterPath = this.posterPath ?: emptyString(),
        backdropPath = this.backdropPath ?: emptyString(),
        releaseDate = this.releaseDate,
        popularity = this.popularity,
        voteAverage = this.voteAverage,
        id = this.id,
        adult = this.adult,
        voteCount = this.voteCount
    )
}

fun MovieDetailResponse.toDomain(): MovieDetail {
    return MovieDetail(
        originalLanguage = this.originalLanguage,
        imdbId = this.imdbId ?: emptyString(),
        video = this.video,
        title = this.title,
        backdropPath = this.backdropPath,
        revenue = this.revenue,
        genres = this.genres.toDomain(),
        popularity = this.popularity,
        id = this.id,
        voteCount = this.voteCount,
        budget = this.budget,
        overview = this.overview,
        originalTitle = this.originalTitle,
        runtime = this.runtime,
        posterPath = this.posterPath,
        originCountry = this.originCountry,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        tagline = this.tagline,
        adult = this.adult,
        homepage = this.homepage,
        status = this.status,
        castCrew = emptyList()
    )
}