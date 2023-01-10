package com.example.movee.data.repository.detail

import com.example.movee.data.repository.genre.Genre
import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    val genres: List<Genre>,
    val overview: String,
    val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val rating: Double
)