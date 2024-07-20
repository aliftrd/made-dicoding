package com.github.aliftrd.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aliftrd.core.data.favorite.model.FavoriteItem
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.favorite.FavoriteUseCase
import com.github.aliftrd.core.domain.favorite.model.Favorite
import com.github.aliftrd.core.domain.movie.MovieUseCase
import com.github.aliftrd.core.domain.movie.model.MovieDetail
import kotlinx.coroutines.launch

class DetailViewModel (private val useCase: MovieUseCase, private val favoriteUseCase: FavoriteUseCase): ViewModel() {
    private val _detailMovile = MutableLiveData<RemoteResponse<MovieDetail>>()
    val detailMovie: LiveData<RemoteResponse<MovieDetail>> get() = _detailMovile

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            useCase.getMovieDetail(id).collect {
                _detailMovile.value = it
            }
        }
    }

    fun addFavoriteMovie(favoriteItem: Favorite) {
        viewModelScope.launch {
            favoriteUseCase.addFavoriteMovie(favoriteItem)
            _isFavorite.value = true
        }
    }

    fun removeFavoriteMovie(favoriteItem: Favorite) {
        viewModelScope.launch {
            favoriteUseCase.removeFavoriteMovie(favoriteItem.movieId)
            _isFavorite.value = false
        }
    }

    fun checkIsFavorite(movieId: Int) {
        viewModelScope.launch {
            favoriteUseCase.isFavorite(movieId).collect {
                _isFavorite.value = it
            }
        }
    }
}