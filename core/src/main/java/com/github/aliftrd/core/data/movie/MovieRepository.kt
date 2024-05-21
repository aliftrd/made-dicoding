package com.github.aliftrd.core.data.movie

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllPopular(): Flow<RemoteResponse<List<Movie>>>
    fun getNowPlaying(): Flow<RemoteResponse<List<Movie>>>
}