package com.github.aliftrd.core.domain.movie.model

import android.os.Parcelable
import com.github.aliftrd.core.domain.genre.model.Genre
import com.github.aliftrd.core.utils.ext.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String = emptyString(),
    val originalLanguage: String = emptyString(),
    val originalTitle: String = emptyString(),
    val video: Boolean = false,
    val title: String = emptyString(),
    val genreIds: List<Int> = emptyList(),
    var genre: List<Genre> = emptyList(),
    val posterPath: String = emptyString(),
    val backdropPath: String = emptyString(),
    val releaseDate: String = emptyString(),
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val id: Int = 0,
    val adult: Boolean = false,
    val voteCount: Int = 0
) : Parcelable