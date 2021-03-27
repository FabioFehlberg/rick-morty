package com.fehlves.rickmorty.data

import android.os.Parcelable
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.common.BaseEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    override val id: Int,
    val image: String,
    val location: NameUrl,
    val name: String,
    val origin: NameUrl,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : BaseEntity(), Parcelable

@Parcelize
data class NameUrl(
    val name: String,
    val url: String
) : Parcelable

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