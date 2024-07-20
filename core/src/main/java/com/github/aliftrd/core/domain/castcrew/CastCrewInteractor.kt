package com.github.aliftrd.core.domain.castcrew

import com.github.aliftrd.core.data.castcrew.CastCrewRepository
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class CastInteractor(private val repository: CastCrewRepository) : CastUseCase {
    override fun getCast(movieId: Int): Flow<RemoteResponse<List<CastCrew>>> {
        return repository.getCastCrew(movieId).flowOn(Dispatchers.IO)
    }
}