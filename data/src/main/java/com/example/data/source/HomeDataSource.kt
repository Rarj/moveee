package com.example.data.source

import android.util.Log
import com.example.data.network.ApiInterface
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeDataSource : KoinComponent {

    private val service by inject<ApiInterface>()
    suspend fun getMovie() {
        Log.e("ASDW", HomeDataSource::class.java.name)
    }
}