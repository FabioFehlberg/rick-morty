package com.fehlves.rickmorty.features.catalogue.model

data class LocationCardView(
    override val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    var onClick: (() -> Unit)? = null
) : CatalogueView()