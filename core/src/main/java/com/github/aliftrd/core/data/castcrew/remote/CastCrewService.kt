package com.github.aliftrd.core.data.castcrew.remote

import com.github.aliftrd.core.data.castcrew.model.CastCrewResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CastCrewService {
    @GET("movie/{movie_id}/credits")
    suspend fun getCastCrew(@Path("movie_id") movieId: Int): CastCrewResponse
}