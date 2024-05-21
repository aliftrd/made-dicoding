package com.github.aliftrd.core.data.genre.remote

import com.github.aliftrd.core.data.genre.model.GenreResponse
import retrofit2.http.GET

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getMovieGenre(): GenreResponse
}