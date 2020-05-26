package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.EpisodeEntity
import com.google.gson.annotations.SerializedName

data class EpisodeResultEntity(
    @SerializedName("info")
    val info: InfoEntity,
    @SerializedName("results")
    val results: List<EpisodeEntity>
)