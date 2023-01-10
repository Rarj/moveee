package com.example.movee.feature.discovermovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movee.data.repository.discover.DiscoverMovie
import com.example.movee.data.repository.discover.DiscoverMovieRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DiscoverMovieViewModel(
    private val repo: DiscoverMovieRepositoryImpl
) : ViewModel() {

    private var _discoverMovies = MutableStateFlow<PagingData<DiscoverMovie>?>(PagingData.empty())
    val discoverMovie get() = _discoverMovies.asStateFlow()
    private var latestGenreId = 0

    fun isGenreHasSelected() = latestGenreId != 0

    fun discoverMovie(genreID: Int) {
        if (latestGenreId != 0 && genreID != latestGenreId) {
            _discoverMovies.value = null
        } else {
            latestGenreId = genreID
        }


        viewModelScope.launch {
            val response = repo.discoverMovie(genreID).cachedIn(viewModelScope)
            response.collect { movies ->
                _discoverMovies.value = movies
            }
            response.catch { _discoverMovies.value = PagingData.empty() }
        }
    }

}