package com.example.movee.data.repository.video

data class VideoResponse(
    val results: List<Video>
)

data class Video(
    val name: String, val key: String, val official: Boolean
)