package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repo.home.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    fun getMovie() {
        viewModelScope.launch {
            useCase.getMovie()
            Log.e("ASDW", HomeViewModel::class.java.name)
        }
    }
}