package com.github.aliftrd.core.data.favorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieId: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genre: String,
    val runtime: Int
)