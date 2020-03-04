package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.google.gson.annotations.SerializedName

data class LocationEntity(
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: Int,
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
)

fun LocationEntity.toLocationCardView(): LocationCardView {
    return LocationCardView(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        url = url
    )
}