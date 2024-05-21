package com.github.aliftrd.core.data.genre.model

data class GenreResponse(
	val genres: List<GenreItem>
)

data class GenreItem(
	val name: String,
	val id: Int
)

