package com.example.movee.data.repository.review

import com.example.movee.network.ApiService

class ReviewDataSource(private val apiService: ApiService) {
    suspend fun reviews(movieID: Int, page: Int) = apiService.reviews(movieID, page)
}