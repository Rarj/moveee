package com.example.movee.data.repository.genre

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GenreRepositoryImpl(private val source: GenreDataSource) : GenreRepository {
    override suspend fun getGenres(): Flow<GenreResponse> {
        return flow {
            val response = source.getGenres()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}