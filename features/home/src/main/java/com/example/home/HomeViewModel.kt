package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repo.home.HomeUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val useCase by inject<HomeUseCase>()

    fun getMovie() {
        viewModelScope.launch {
            useCase.getMovie()
        }
    }
}