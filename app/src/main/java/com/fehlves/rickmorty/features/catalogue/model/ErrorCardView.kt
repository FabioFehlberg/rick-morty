package com.fehlves.rickmorty.features.catalogue.model

data class ErrorCardView(
    override val id: Int = Int.MIN_VALUE,
    val action: () -> Unit
) : CatalogueView()