package com.example.movee.data.repository.video

import com.example.movee.network.ApiService

class VideoDataSource(private val apiService: ApiService) {
    suspend fun video(movieId: Int) = apiService.video(movieId)
}