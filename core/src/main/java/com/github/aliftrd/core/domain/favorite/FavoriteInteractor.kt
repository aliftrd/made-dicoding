package com.github.aliftrd.core.domain.favorite

import com.github.aliftrd.core.data.favorite.FavoriteRepository
import com.github.aliftrd.core.domain.favorite.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class FavoriteInteractor(private val repository: FavoriteRepository) : FavoriteUseCase {
    override suspend fun addFavoriteMovie(favorite: Favorite) = repository.addFavoriteItem(favorite)
    override suspend fun removeFavoriteMovie(id: Int) = repository.removeFavoriteItem(id)

    override fun getFavoriteMovie(): Flow<List<Favorite>> {
        return repository.getFavoriteItems().flowOn(Dispatchers.IO)
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return repository.isFavorite(id).flowOn(Dispatchers.IO)
    }

}