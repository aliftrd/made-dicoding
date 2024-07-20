package com.github.aliftrd.core.data.castcrew

import com.github.aliftrd.core.data.castcrew.remote.CastCrewService
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.cast.mapper.toDomain
import com.github.aliftrd.core.domain.castcrew.mapper.toDomain
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CastCrewDataStore(private val castCrewService: CastCrewService) : CastCrewRepository {
    override fun getCastCrew(movieId: Int): Flow<RemoteResponse<List<CastCrew>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val response = castCrewService.getCastCrew(movieId)
            val cast = response.cast.toDomain()
            val crew = response.crew.toDomain()
            val castCrew: List<CastCrew> = cast + crew
            emit(RemoteResponse.Success(castCrew))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResponse.Error(e.toString()))
        }
    }

}