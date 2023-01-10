package com.example.movee.data.repository.review

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun reviews(movieId: Int): Flow<PagingData<Review>>
}