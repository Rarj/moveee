package com.example.movee.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.detail.DetailMovieRepositoryImpl
import com.example.movee.data.repository.detail.DetailMovieResponse
import com.example.movee.network.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val repo: DetailMovieRepositoryImpl
) : ViewModel() {

    private var _detailMovie = MutableStateFlow<ViewState<DetailMovieResponse>>(ViewState.Loading(null))
    val detailMovie get() = _detailMovie

    fun detailMovie(id: Int) {
        viewModelScope.launch {
            val response = repo.detailMovie(id)
            response.collect { movie ->
                _detailMovie.value = ViewState.Success(movie)
            }
            response.catch { throwable ->
                _detailMovie.value = ViewState.Error(null, throwable.message.toString())
            }
        }
    }

}