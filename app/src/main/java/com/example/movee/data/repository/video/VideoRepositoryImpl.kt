package com.example.movee.data.repository.video

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideoRepositoryImpl(
    private val dataSource: VideoDataSource
) : VideoRepository {
    override suspend fun video(movieId: Int): Flow<VideoResponse> {
        return flow {
            val response = dataSource.video(movieId).results.filter { it.official }
            emit(VideoResponse(response))
        }.flowOn(Dispatchers.IO)
    }
}