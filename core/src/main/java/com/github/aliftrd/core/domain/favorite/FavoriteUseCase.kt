package com.github.aliftrd.core.domain.favorite

import com.github.aliftrd.core.domain.favorite.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    suspend fun addFavoriteMovie(favorite: Favorite)
    suspend fun removeFavoriteMovie(id: Int)
    fun isFavorite(id: Int): Flow<Boolean>
    fun getFavoriteMovie(): Flow<List<Favorite>>
}