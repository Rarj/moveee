package com.example.core

object Secret {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
    external fun youtubeKey(): String
}