package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseEntity
import com.google.gson.annotations.SerializedName

data class LocationEntity(
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    override val id: Int,
    @SerializedName("residents")
    val residents: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
): BaseEntity()

fun LocationEntity.toLocationCardView(): LocationCardView {
    return LocationCardView(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}