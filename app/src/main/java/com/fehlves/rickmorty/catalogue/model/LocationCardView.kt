package com.fehlves.rickmorty.catalogue.model

data class LocationCardView(
    override val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val onClick: () -> Unit
) : CatalogueView()