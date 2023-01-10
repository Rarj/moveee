package com.example.movee.feature.caster.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movee.data.repository.review.Review
import com.example.movee.data.repository.review.ReviewRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val repo: ReviewRepositoryImpl
) : ViewModel() {

    private var _reviewMovies = MutableStateFlow<PagingData<Review>?>(null)
    val reviewMovie get() = _reviewMovies.asStateFlow()

    fun reviews(movieId: Int) {
        viewModelScope.launch {
            val response = repo.reviews(movieId).cachedIn(this)
            response.collect { movies ->
                _reviewMovies.value = movies
            }
            response.catch {
                _reviewMovies.value = PagingData.empty()
            }
        }
    }
}