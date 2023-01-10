package com.example.movee.data.repository.genre

import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenres(): Flow<GenreResponse>
}