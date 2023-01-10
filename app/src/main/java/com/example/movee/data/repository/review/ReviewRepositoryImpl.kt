package com.example.movee.data.repository.review

import androidx.paging.PagingData
import com.example.movee.paging.BasePagingSource
import com.example.movee.paging.PageUtils
import com.example.movee.paging.PagingDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ReviewRepositoryImpl(
    private val source: ReviewDataSource
) : ReviewRepository {
    override suspend fun reviews(movieId: Int): Flow<PagingData<Review>> {
        return PageUtils.createPager {
            BasePagingSource { loadParams ->
                val reviews = mutableListOf<Review>()
                var isSuccess = true
                var errorBody: Exception? = null
                var pageCount = PageUtils.FIRST_PAGE

                val response =
                    source.reviews(movieId, (loadParams.key ?: PageUtils.FIRST_PAGE))

                if (response.results.isNotEmpty()) {
                    reviews.addAll(response.results)
                    pageCount = response.page + 1
                } else {
                    isSuccess = false
                    errorBody = Exception("Terjadi kesalahan!")
                }

                PagingDataModel(
                    data = reviews,
                    pageCount = pageCount,
                    isSuccess = isSuccess,
                    errorBody = errorBody
                )
            }
        }.flow.flowOn(Dispatchers.IO)
    }
}