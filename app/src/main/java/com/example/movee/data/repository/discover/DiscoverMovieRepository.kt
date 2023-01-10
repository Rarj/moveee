package com.example.movee.data.repository.discover

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {
    suspend fun discoverMovie(genreID: Int): Flow<PagingData<DiscoverMovie>>
}