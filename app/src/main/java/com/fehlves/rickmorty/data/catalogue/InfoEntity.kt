package com.fehlves.rickmorty.data.catalogue

import com.google.gson.annotations.SerializedName

data class InfoEntity(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String
)