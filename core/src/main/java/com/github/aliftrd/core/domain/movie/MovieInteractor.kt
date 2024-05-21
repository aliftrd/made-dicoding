package com.github.aliftrd.core.domain.movie

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.data.movie.MovieRepository
import com.github.aliftrd.core.domain.movie.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieInteractor(private val repository: MovieRepository): MovieUseCase {
    override fun getAllPopular(): Flow<RemoteResponse<List<Movie>>> {
        return repository.getAllPopular().flowOn(Dispatchers.IO)
    }

    override fun getNowPlaying(): Flow<RemoteResponse<List<Movie>>> {
        return repository.getNowPlaying().flowOn(Dispatchers.IO)
    }
}