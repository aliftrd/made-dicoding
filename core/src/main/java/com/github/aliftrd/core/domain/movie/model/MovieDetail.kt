package com.github.aliftrd.core.domain.movie.model

import android.os.Parcelable
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import com.github.aliftrd.core.domain.genre.model.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    val originalLanguage: String,
    val imdbId: String,
    val video: Boolean,
    val title: String,
    val backdropPath: String,
    val revenue: Int,
    val genres: List<Genre>,
    val popularity: Double,
    val id: Int,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String,
    val originCountry: List<String>,
    val releaseDate: String,
    val voteAverage: Double,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String,
    var castCrew: List<CastCrew>
) : Parcelable