package com.fehlves.rickmorty.data

import android.os.Parcelable
import com.fehlves.rickmorty.common.BaseEntity
import com.fehlves.rickmorty.features.catalogue.model.LocationCardView
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationEntity(
    val created: String,
    override val id: Int,
    val residents: List<String>,
    val name: String,
    val dimension: String,
    val type: String,
    val url: String
) : BaseEntity(), Parcelable

fun LocationEntity.toLocationCardView(): LocationCardView {
    return LocationCardView(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}