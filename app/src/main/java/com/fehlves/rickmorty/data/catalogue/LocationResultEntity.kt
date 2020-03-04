package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.LocationEntity
import com.google.gson.annotations.SerializedName

data class LocationResultEntity(
    @SerializedName("info")
    val info: InfoEntity,
    @SerializedName("results")
    val results: List<LocationEntity>
)