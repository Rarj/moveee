package com.example.movee.data.repository.review

import com.example.movee.BuildConfig
import com.example.movee.R
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val results: List<Review>, val page: Int
)

data class Review(
    val id: String,
    @SerializedName("author_details") val author: Author,
    @SerializedName("content") val review: String
)

data class Author(
    val username: String, @SerializedName("avatar_path") val avatarPath: String?, val rating: Double
) {
    fun getAvatarUrl() = if (avatarPath.isNullOrEmpty()) {
        R.drawable.ic_person
    } else {
        buildString {
            append(BuildConfig.BASE_URL_IMAGE)
            append(avatarPath)
        }
    }
}