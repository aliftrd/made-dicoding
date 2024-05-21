package com.github.aliftrd.core.data.movie.remote

import com.github.aliftrd.core.data.movie.model.BasicMovieResponse
import com.github.aliftrd.core.data.movie.model.NowPlayingMovieResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): BasicMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingMovieResponse
}