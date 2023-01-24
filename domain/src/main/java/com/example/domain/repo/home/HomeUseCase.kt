package com.example.domain.repo.home

import android.util.Log

class HomeUseCase(private val repo: HomeRepository) {
    suspend fun getMovie() {
        repo.discoverMovie(1)
        Log.e("ASDW", HomeUseCase::class.java.name)
    }
}