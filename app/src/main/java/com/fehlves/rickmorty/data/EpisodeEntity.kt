package com.fehlves.rickmorty.data

import android.os.Parcelable
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.common.BaseEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeEntity(
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    override val id: Int,
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
): BaseEntity(), Parcelable

fun EpisodeEntity.toEpisodeCardView(): EpisodeCardView {
    return EpisodeCardView(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode
    )
}