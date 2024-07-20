package com.github.aliftrd.core.domain.movie

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.data.movie.MovieRepository
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.domain.movie.model.MovieDetail
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

    override fun getMovieDetail(id: Int): Flow<RemoteResponse<MovieDetail>> {
        return repository.getMovieDetail(id).flowOn(Dispatchers.IO)
    }

    override fun searchMovie(query: String): Flow<RemoteResponse<List<Movie>>> {
        return repository.searchMovie(query).flowOn(Dispatchers.IO)
    }
}