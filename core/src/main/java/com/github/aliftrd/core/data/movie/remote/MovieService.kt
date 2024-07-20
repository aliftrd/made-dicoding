package com.github.aliftrd.core.data.movie.remote

import com.github.aliftrd.core.data.movie.model.BasicMovieResponse
import com.github.aliftrd.core.data.movie.model.MovieDetailResponse
import com.github.aliftrd.core.data.movie.model.NowPlayingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): BasicMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingMovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetailResponse

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): BasicMovieResponse
}