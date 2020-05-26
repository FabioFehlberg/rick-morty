package com.fehlves.rickmorty.catalogue.model

data class CharacterCardView(
    override val id: Int,
    val name: String,
    val specie: String,
    val gender: String,
    val status: String,
    val image: String,
    var onClick: (() -> Unit)? = null
) : CatalogueView()