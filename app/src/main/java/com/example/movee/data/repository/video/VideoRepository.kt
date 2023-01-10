package com.example.movee.data.repository.video

import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun video(movieId: Int): Flow<VideoResponse>
}