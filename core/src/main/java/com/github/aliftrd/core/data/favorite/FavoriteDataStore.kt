package com.github.aliftrd.core.data.favorite

import android.util.Log
import com.github.aliftrd.core.data.favorite.local.FavoriteDao
import com.github.aliftrd.core.data.favorite.model.FavoriteItem
import com.github.aliftrd.core.domain.favorite.mapper.toDomain
import com.github.aliftrd.core.domain.favorite.model.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteDataStore(private val favoriteDao: FavoriteDao): FavoriteRepository {
    override fun getFavoriteItems(): Flow<List<Favorite>> = flow {
        val favoriteItems = favoriteDao.getAllMovie()
        emit(favoriteItems.toDomain())
    }

    override suspend fun addFavoriteItem(favoriteItem: Favorite) {
        val favorite = FavoriteItem(
            movieId = favoriteItem.movieId,
            title = favoriteItem.title,
            posterPath = favoriteItem.posterPath,
            releaseDate = favoriteItem.releaseDate,
            voteAverage = favoriteItem.voteAverage,
            id = favoriteItem.id,
            genre = favoriteItem.genre,
            runtime = favoriteItem.runtime
        )

        favoriteDao.insertMovie(favorite)
    }

    override fun isFavorite(movieId: Int): Flow<Boolean> = flow {
        val favoriteItem = favoriteDao.getMovie(movieId)
        emit(favoriteItem != null)
    }

    override suspend fun removeFavoriteItem(movieId: Int) {
        favoriteDao.deleteMovie(movieId)
    }
}