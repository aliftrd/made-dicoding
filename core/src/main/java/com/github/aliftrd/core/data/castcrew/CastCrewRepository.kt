package com.github.aliftrd.core.data.castcrew

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import kotlinx.coroutines.flow.Flow

interface CastCrewRepository {
    fun getCastCrew(movieId: Int): Flow<RemoteResponse<List<CastCrew>>>
}