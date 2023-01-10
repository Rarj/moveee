package com.example.movee.data.repository.detail

import com.example.movee.network.ApiService

class DetailMovieDataSource(private val apiService: ApiService) {
    suspend fun detailMovie(id: Int) = apiService.detailMovie(id)
}