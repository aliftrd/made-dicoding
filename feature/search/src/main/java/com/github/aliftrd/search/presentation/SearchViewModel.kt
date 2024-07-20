package com.github.aliftrd.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.movie.MovieUseCase
import com.github.aliftrd.core.domain.movie.model.Movie
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: MovieUseCase) : ViewModel() {
    private val _searchedMovie = MutableLiveData<RemoteResponse<List<Movie>>>()
    val searchedMovie: LiveData<RemoteResponse<List<Movie>>> get() = _searchedMovie

    fun searchMovie(query: String) {
        viewModelScope.launch {
            useCase.searchMovie(query).collect {
                _searchedMovie.value = it
            }
        }
    }
}