package com.github.aliftrd.core.data.movie

import android.util.Log
import com.github.aliftrd.core.data.genre.remote.GenreService
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.data.movie.remote.MovieService
import com.github.aliftrd.core.domain.genre.mapper.toDomain
import com.github.aliftrd.core.domain.movie.mapper.toDomain
import com.github.aliftrd.core.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDataStore(
    private val genreService: GenreService,
    private val movieService: MovieService
) : MovieRepository {
    override fun getAllPopular(): Flow<RemoteResponse<List<Movie>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val movieResponse = movieService.getPopularMovies()
            val genresResponse = genreService.getMovieGenre()
            val genres = genresResponse.genres.toDomain()
            val movie = movieResponse.results.toDomain()


            if (movie.isNotEmpty() && genres.isNotEmpty()) {
                movie.map {
                    val genre = it.genreIds.map { genreId ->
                        genres.find { it.id == genreId }
                    }.filterNotNull()

                    it.genre = genre
                }
                emit(RemoteResponse.Success(movie))
            } else {
                emit(RemoteResponse.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun getNowPlaying(): Flow<RemoteResponse<List<Movie>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val movieResponse = movieService.getNowPlayingMovies()
            val genresResponse = genreService.getMovieGenre()
            val genres = genresResponse.genres.toDomain()
            val movie = movieResponse.results.toDomain()

            if (movie.isNotEmpty() && genres.isNotEmpty()) {
                movie.map {
                    val genre = it.genreIds.map { genreId ->
                        genres.find { it.id == genreId }
                    }.filterNotNull()

                    it.genre = genre
                }
                emit(RemoteResponse.Success(movie))
            } else {
                emit(RemoteResponse.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }
}