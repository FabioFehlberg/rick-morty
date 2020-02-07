package com.fehlves.rickmorty.data

import com.google.gson.annotations.SerializedName

data class CharacterEntity(
    @SerializedName("id")
    val id: Int
)