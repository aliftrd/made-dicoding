package com.github.aliftrd.core.domain.castcrew.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastCrew(
    val name: String,
    val job: String,
    val profilePath: String? = null,
): Parcelable