package com.github.aliftrd.core.domain.movie.model

import android.os.Parcelable
import com.github.aliftrd.core.domain.genre.model.Genre
import com.github.aliftrd.core.utils.ext.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val genreIds: List<Int>,
    var genre: List<Genre>,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int
) : Parcelable