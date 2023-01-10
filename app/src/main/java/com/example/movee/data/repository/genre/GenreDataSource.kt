package com.example.movee.data.repository.genre

import com.example.movee.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GenreDataSource(private val apiService: ApiService) {
    suspend fun getGenres() = apiService.getGenres()
}