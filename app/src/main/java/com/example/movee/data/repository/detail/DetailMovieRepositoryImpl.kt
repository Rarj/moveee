package com.example.movee.data.repository.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailMovieRepositoryImpl(
    private val source: DetailMovieDataSource
) : DetailMovieRepository {
    override suspend fun detailMovie(id: Int): Flow<DetailMovieResponse> {
        return flow {
            val response = source.detailMovie(id)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}