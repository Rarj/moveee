package com.example.movee.network

import com.example.movee.data.repository.caster.CasterResponse
import com.example.movee.data.repository.detail.DetailMovieResponse
import com.example.movee.data.repository.discover.DiscoverMovieResponse
import com.example.movee.data.repository.genre.GenreResponse
import com.example.movee.data.repository.review.ReviewResponse
import com.example.movee.data.repository.video.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): DiscoverMovieResponse

    @GET("movie/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") id: Int): DetailMovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun casters(@Path("movie_id") id: Int): CasterResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun reviews(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): ReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun video(@Path("movie_id") id: Int): VideoResponse
}