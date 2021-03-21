package com.fehlves.rickmorty.data

import android.os.Parcelable
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.common.BaseEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeEntity(
    val episode: String,
    override val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    val name: String,
    val characters: List<String>,
    val type: String,
    val created: String,
    val url: String
) : BaseEntity(), Parcelable

fun EpisodeEntity.toEpisodeCardView(): EpisodeCardView {
    return EpisodeCardView(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode
    )
}