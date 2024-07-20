package com.github.aliftrd.core.domain.castcrew.mapper

import com.github.aliftrd.core.data.castcrew.model.CastItem
import com.github.aliftrd.core.domain.castcrew.model.CastCrew

fun List<CastItem>.toDomain(): List<CastCrew> {
    return this.map {
        it.toDomain()
    }
}

fun CastItem.toDomain(): CastCrew {
    return CastCrew(
        name = this.name,
        job = this.character,
        profilePath = this.profilePath
    )
}