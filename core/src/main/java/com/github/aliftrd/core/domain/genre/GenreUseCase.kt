package com.github.aliftrd.core.domain.genre

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.genre.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreUseCase {
    fun getMovieGenre(): Flow<RemoteResponse<List<Genre>>>
}