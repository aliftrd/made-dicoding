package com.github.aliftrd.core.data.movie.model

import com.google.gson.annotations.SerializedName

abstract class BaseMovieResponse {
    @field:SerializedName("page")
    val page: Int = 1

    @field:SerializedName("total_pages")
    val totalPages: Int = 1

    @field:SerializedName("total_results")
    val totalResults: Int = 1
}

data class BasicMovieResponse(
    @field:SerializedName("results")
    val results: List<MovieItem> = emptyList()
) : BaseMovieResponse()

data class NowPlayingMovieResponse(
    @field:SerializedName("dates")
    val dates: Dates,

    @field:SerializedName("results")
    val results: List<MovieItem> = emptyList()
) : BaseMovieResponse()

data class MovieItem(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("vote_count")
    val voteCount: Int
)

data class Dates(
    @field:SerializedName("maximum")
    val maximum: String,

    @field:SerializedName("minimum")
    val minimum: String
)