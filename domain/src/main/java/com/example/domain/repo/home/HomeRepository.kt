package com.example.domain.repo.home

import com.example.domain.repo.response.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

interface HomeRepository: KoinComponent {
    suspend fun discoverMovie(genreID: Int): Flow<DiscoverMovie>
}