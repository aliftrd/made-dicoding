package com.github.aliftrd.core.di.feature.castcrew

import com.github.aliftrd.core.data.castcrew.CastCrewDataStore
import com.github.aliftrd.core.data.castcrew.CastCrewRepository
import com.github.aliftrd.core.domain.castcrew.CastInteractor
import com.github.aliftrd.core.domain.castcrew.CastUseCase
import org.koin.dsl.module

val castModule = module {
    factory<CastUseCase> { CastInteractor(get()) }
    factory<CastCrewRepository> { CastCrewDataStore(get()) }

    single { CastCrewDataStore(get()) }
    single { CastInteractor(get()) }
}