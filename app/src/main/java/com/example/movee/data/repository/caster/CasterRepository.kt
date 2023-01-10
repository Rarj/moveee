package com.example.movee.data.repository.caster

import kotlinx.coroutines.flow.Flow

interface CasterRepository {
    suspend fun casters(movieID: Int): Flow<CasterResponse>
    suspend fun detailCasters(movieID: Int): Flow<CasterResponse>
}