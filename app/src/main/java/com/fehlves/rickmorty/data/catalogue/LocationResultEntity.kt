package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.LocationEntity

data class LocationResultEntity(
    val info: InfoEntity,
    val results: List<LocationEntity>
)