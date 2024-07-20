package com.github.aliftrd.core.domain.cast.mapper

import com.github.aliftrd.core.data.castcrew.model.CrewItem
import com.github.aliftrd.core.domain.castcrew.model.CastCrew


fun List<CrewItem>.toDomain(): List<CastCrew> {
    return this.map {
        it.toDomain()
    }
}

fun CrewItem.toDomain(): CastCrew {
    return CastCrew(
        name = this.name,
        job = this.job,
        profilePath = this.profilePath
    )
}