package com.example.movee.data.repository.caster

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CasterRepositoryImpl(
    private val source: CasterDataSource
) : CasterRepository {
    override suspend fun casters(movieID: Int): Flow<CasterResponse> {
        val casters = mutableListOf<Caster>()
        return flow {
            val response = source.casters(movieID)
            if (response.cast.size > 5) {
                casters.addAll(response.cast.slice(0..4))
            } else {
                casters.addAll(response.cast)
            }
            emit(CasterResponse(casters))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun detailCasters(movieID: Int): Flow<CasterResponse> {
        return flow {
            val response = source.casters(movieID)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}