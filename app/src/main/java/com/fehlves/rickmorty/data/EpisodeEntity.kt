package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.common.BaseEntity
import com.fehlves.rickmorty.features.catalogue.model.EpisodeCardView
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeEntity(
    val episode: String,
    override val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    val name: String,
    val characters: List<String>,
    val created: String,
    val url: String
) : BaseEntity()

fun EpisodeEntity.toEpisodeCardView(): EpisodeCardView {
    return EpisodeCardView(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode
    )
}