package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.EpisodeEntity

data class EpisodeResultEntity(
    val info: InfoEntity,
    val results: List<EpisodeEntity>
)