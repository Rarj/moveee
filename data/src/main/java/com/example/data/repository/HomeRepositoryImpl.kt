package com.example.data.repository

import android.util.Log
import com.example.data.source.HomeDataSource
import com.example.domain.repo.home.HomeRepository
import com.example.domain.repo.response.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun discoverMovie(genreID: Int): Flow<DiscoverMovie> {
        Log.e("ASDW", HomeRepositoryImpl::class.java.name)
            dataSource.getMovie()
        return flow {
        }
    }

}