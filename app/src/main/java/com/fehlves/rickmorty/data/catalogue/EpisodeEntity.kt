package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.google.gson.annotations.SerializedName

data class EpisodeEntity(
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("url")
    val url: String
)

fun EpisodeEntity.toEpisodeCardView(): EpisodeCardView {
    return EpisodeCardView(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        url = url
    )
}