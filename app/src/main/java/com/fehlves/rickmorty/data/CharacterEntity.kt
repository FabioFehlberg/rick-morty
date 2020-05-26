package com.fehlves.rickmorty.data

import android.os.Parcelable
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.common.BaseEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterEntity(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    override val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : BaseEntity(), Parcelable

@Parcelize
data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Parcelable

@Parcelize
data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Parcelable

fun CharacterEntity.toCharacterCardView(): CharacterCardView {
    return CharacterCardView(
        id = id,
        name = name,
        specie = species,
        gender = gender,
        status = status,
        image = image
    )
}