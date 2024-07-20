package com.github.aliftrd.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aliftrd.core.domain.favorite.FavoriteUseCase
import com.github.aliftrd.core.domain.favorite.model.Favorite
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase): ViewModel() {
    private val _favorite = MutableLiveData<List<Favorite>>()
    val favorite: LiveData<List<Favorite>> = _favorite

    fun getFavoriteMovie() {
        viewModelScope.launch {
            favoriteUseCase.getFavoriteMovie().collect {
                _favorite.value = it
            }
        }
    }
}