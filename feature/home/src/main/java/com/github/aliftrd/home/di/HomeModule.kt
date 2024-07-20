package com.github.aliftrd.home.di

import com.github.aliftrd.detail.presentation.DetailViewModel
import com.github.aliftrd.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}