package com.example.di

import com.example.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelInjector = module {
    viewModel { HomeViewModel() }
}