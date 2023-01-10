package com.example.movee.data.repository.discover

import androidx.paging.PagingData
import com.example.movee.paging.BasePagingSource
import com.example.movee.paging.PageUtils
import com.example.movee.paging.PagingDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DiscoverMovieRepositoryImpl(private val dataSource: DiscoverMovieDataSource) :
    DiscoverMovieRepository {
    override suspend fun discoverMovie(
        genreID: Int
    ): Flow<PagingData<DiscoverMovie>> {
        return PageUtils.createPager {
            BasePagingSource { loadParams ->
                val movies = mutableListOf<DiscoverMovie>()
                var isSuccess = true
                var errorBody: Exception? = null
                var pageCount = PageUtils.FIRST_PAGE

                val response =
                    dataSource.discoverMovies(genreID, (loadParams.key ?: PageUtils.FIRST_PAGE))

                if (response.results.isNotEmpty()) {
                    movies.addAll(response.results)
                    pageCount = response.page + 1
                } else {
                    isSuccess = false
                    errorBody = Exception("Terjadi kesalahan!")
                }

                PagingDataModel(
                    data = movies,
                    pageCount = pageCount,
                    isSuccess = isSuccess,
                    errorBody = errorBody
                )
            }
        }.flow.flowOn(Dispatchers.IO)
    }
}