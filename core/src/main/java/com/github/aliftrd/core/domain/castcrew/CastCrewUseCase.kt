package com.github.aliftrd.core.domain.castcrew

import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import kotlinx.coroutines.flow.Flow

interface CastUseCase {
    fun getCast(movieId: Int): Flow<RemoteResponse<List<CastCrew>>>
}