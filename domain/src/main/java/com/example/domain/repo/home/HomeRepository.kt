package com.example.domain.repo.home

import com.example.domain.repo.response.DiscoverMovie
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun discoverMovie(genreID: Int): Flow<DiscoverMovie>
}