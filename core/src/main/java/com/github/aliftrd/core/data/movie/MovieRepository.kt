package com.github.aliftrd.core.data.movie

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllPopular(): Flow<RemoteResponse<List<Movie>>>
    fun getNowPlaying(): Flow<RemoteResponse<List<Movie>>>
    fun getMovieDetail(id: Int): Flow<RemoteResponse<MovieDetail>>
    fun searchMovie(query: String): Flow<RemoteResponse<List<Movie>>>
}