package com.example.movee.data.repository.caster

import com.example.movee.BuildConfig
import com.google.gson.annotations.SerializedName

data class CasterResponse(
    val cast: List<Caster>
)

data class Caster(
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    val character: String
) {
    fun getProfileUrl() = buildString {
        append(BuildConfig.BASE_URL_IMAGE)
        append(profilePath)
    }
}