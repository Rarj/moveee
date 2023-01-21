package com.example.core.network

import com.example.core.response.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("with_genres") genreId: Int, @Query("page") page: Int
    ): DiscoverMovieResponse

}