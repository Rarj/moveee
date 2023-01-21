package com.example.domain.repo.response

import com.google.gson.annotations.SerializedName

data class DiscoverMovieResponse(
    val results: List<DiscoverMovie>,
    val page: Int
)

data class DiscoverMovie(
    val id: Int, @SerializedName("backdrop_path") val backdrop: String, val title: String
)