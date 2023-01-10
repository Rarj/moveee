package com.example.movee.data.repository.detail

import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    suspend fun detailMovie(id: Int): Flow<DetailMovieResponse>
}