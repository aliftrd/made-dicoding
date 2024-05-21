package com.github.aliftrd.core.domain.genre.mapper

import com.github.aliftrd.core.data.genre.model.GenreItem
import com.github.aliftrd.core.domain.genre.model.Genre

fun List<GenreItem>.toDomain(): List<Genre> {
    return this.map {
        it.toDomain()
    }
}

fun GenreItem.toDomain(): Genre {
    return Genre(
        name = this.name,
        id = this.id
    )
}