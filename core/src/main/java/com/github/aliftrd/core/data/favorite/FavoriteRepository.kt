package com.github.aliftrd.core.data.favorite

import com.github.aliftrd.core.domain.favorite.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteItems(): Flow<List<Favorite>>
    suspend fun addFavoriteItem(favoriteItem: Favorite): Unit
    fun isFavorite(movieId: Int): Flow<Boolean>
    suspend fun removeFavoriteItem(movieId: Int)
}