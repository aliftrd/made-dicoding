package com.github.aliftrd.core.data.genre

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.genre.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getMovieGenre(): Flow<RemoteResponse<List<Genre>>>
}