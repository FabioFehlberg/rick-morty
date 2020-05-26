package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.CharacterEntity
import com.google.gson.annotations.SerializedName

data class CharacterResultEntity(
    @SerializedName("info")
    val info: InfoEntity,
    @SerializedName("results")
    val results: List<CharacterEntity>
)