package com.github.aliftrd.core.domain.genre

import com.github.aliftrd.core.data.genre.GenreRepository
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.genre.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GenreInteractor(private val repository: GenreRepository) : GenreUseCase {
    override fun getMovieGenre(): Flow<RemoteResponse<List<Genre>>> {
        return repository.getMovieGenre().flowOn(Dispatchers.IO)
    }
}