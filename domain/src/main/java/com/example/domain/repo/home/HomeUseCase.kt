package com.example.domain.repo.home

import android.util.Log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeUseCase : KoinComponent {
    private val repo by inject<HomeRepository>()
    suspend fun getMovie() {
        repo.discoverMovie(1)
        Log.e("ASDW", HomeUseCase::class.java.name)
    }
}