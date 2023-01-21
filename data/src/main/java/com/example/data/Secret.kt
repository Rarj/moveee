package com.example.data

object Secret {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
    external fun youtubeKey(): String
    external fun baseURL(): String
    external fun baseURLImage(): String
}