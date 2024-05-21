package com.github.aliftrd.core.data.genre

import com.github.aliftrd.core.data.genre.remote.GenreService
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.genre.mapper.toDomain
import com.github.aliftrd.core.domain.genre.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreDataStore(private val genreService: GenreService) : GenreRepository {
    override fun getMovieGenre(): Flow<RemoteResponse<List<Genre>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val response = genreService.getMovieGenre()
            val genres = response.genres.toDomain()
            if(genres.isNotEmpty()) {
                emit(RemoteResponse.Success(genres))
            } else {
                emit(RemoteResponse.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }
}