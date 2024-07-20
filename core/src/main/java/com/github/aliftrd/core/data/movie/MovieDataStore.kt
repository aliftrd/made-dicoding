package com.github.aliftrd.core.data.movie

import com.github.aliftrd.core.data.castcrew.remote.CastCrewService
import com.github.aliftrd.core.data.genre.remote.GenreService
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.data.movie.remote.MovieService
import com.github.aliftrd.core.domain.cast.mapper.toDomain
import com.github.aliftrd.core.domain.castcrew.mapper.toDomain
import com.github.aliftrd.core.domain.genre.mapper.toDomain
import com.github.aliftrd.core.domain.movie.mapper.toDomain
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.domain.movie.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDataStore(
    private val genreService: GenreService,
    private val movieService: MovieService,
    private val castCrewService: CastCrewService,
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
            emit(RemoteResponse.Error("Unknown Error"))
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
            emit(RemoteResponse.Error("Unknown Error"))
        }
    }

    override fun getMovieDetail(id: Int): Flow<RemoteResponse<MovieDetail>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val movieDetailResponse = movieService.getMovieDetail(id)
            val castCrewResponse = castCrewService.getCastCrew(id)
            val movie = movieDetailResponse.toDomain()
            val cast = castCrewResponse.cast.toDomain()
            val crew = castCrewResponse.crew.toDomain()
            movie.castCrew = cast + crew
            emit(RemoteResponse.Success(movie))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResponse.Error("Unknown Error"))
        }
    }

    override fun searchMovie(query: String): Flow<RemoteResponse<List<Movie>>> = flow {
            try {
                emit(RemoteResponse.Loading)
                val movieResponse = movieService.searchMovie(query)
                val genresResponse = genreService.getMovieGenre()
                val genres = genresResponse.genres.toDomain()
                val movies = movieResponse.results.toDomain()

                val filteredMovies = movies.filter { !it.posterPath.isNullOrEmpty() || !it.backdropPath.isNullOrEmpty() }

                if (filteredMovies.isNotEmpty()) {
                    filteredMovies.forEach { movie ->
                        val movieGenres = movie.genreIds.mapNotNull { genreId ->
                            genres.find { it.id == genreId }
                        }
                        movie.genre = movieGenres
                    }
                    emit(RemoteResponse.Success(filteredMovies))
                } else {
                    emit(RemoteResponse.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(RemoteResponse.Error("Unknown Error"))
            }
    }
}