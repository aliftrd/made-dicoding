package com.github.aliftrd.core.domain.favorite.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite(
    val id: Int,
    val movieId: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genre: String,
    val runtime: Int
): Parcelable