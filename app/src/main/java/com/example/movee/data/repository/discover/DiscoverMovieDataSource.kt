package com.example.movee.data.repository.discover

import com.example.movee.network.ApiService

class DiscoverMovieDataSource(private val apiService: ApiService) {
    suspend fun discoverMovies(genreId: Int, page: Int) = apiService.discoverMovie(genreId, page)
}