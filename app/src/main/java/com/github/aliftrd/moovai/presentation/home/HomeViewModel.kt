package com.github.aliftrd.moovai.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.movie.MovieUseCase
import com.github.aliftrd.core.domain.movie.model.Movie
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: MovieUseCase): ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<RemoteResponse<List<Movie>>>()
    val nowPlayingMovies: LiveData<RemoteResponse<List<Movie>>> get() = _nowPlayingMovies

    private val _popularMovies = MutableLiveData<RemoteResponse<List<Movie>>>()
    val popularMovies: LiveData<RemoteResponse<List<Movie>>> get() = _popularMovies

    fun getAllNowPlaying() {
        viewModelScope.launch {
            useCase.getNowPlaying().collect {
                _nowPlayingMovies.value = it
            }
        }
    }

    fun getAllPopular() {
        viewModelScope.launch {
            useCase.getAllPopular().collect {
                _popularMovies.value = it
            }
        }
    }
}