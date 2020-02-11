package com.fehlves.rickmorty.data

import com.google.gson.annotations.SerializedName

data class EpisodeResultEntity(
    @SerializedName("info")
    val info: InfoEntity,
    @SerializedName("results")
    val results: List<EpisodeEntity>
)