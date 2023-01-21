package com.example.home

import androidx.lifecycle.ViewModel
import com.example.domain.repo.home.HomeUseCase

class HomeViewModel(
    private val useCase: HomeUseCase
): ViewModel() {
}