package com.example.data.source

import android.util.Log
import com.example.data.network.ApiInterface

class HomeDataSource(private val service: ApiInterface) {
    suspend fun getMovie() {
        Log.e("ASDW", HomeDataSource::class.java.name)
    }
}