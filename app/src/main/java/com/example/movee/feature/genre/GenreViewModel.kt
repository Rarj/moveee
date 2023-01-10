package com.example.movee.feature.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movee.data.repository.genre.GenreRepositoryImpl
import com.example.movee.data.repository.genre.GenreResponse
import com.example.movee.network.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GenreViewModel(private val repo: GenreRepositoryImpl) : ViewModel() {

    private var _genreResponse = MutableStateFlow<ViewState<GenreResponse>>(ViewState.Loading(null))
    val genreResponse get() = _genreResponse.asStateFlow()

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            val result = repo.getGenres()
            result.collect { response ->
                _genreResponse.value = ViewState.Success(response)
            }
            result.catch {
                _genreResponse.value = ViewState.Error(null, it.message.toString())
            }
        }
    }

}