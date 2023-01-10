package com.example.movee.data.repository.caster

import com.example.movee.network.ApiService

class CasterDataSource(private val apiService: ApiService) {
    suspend fun casters(movieID: Int) = apiService.casters(movieID)
}