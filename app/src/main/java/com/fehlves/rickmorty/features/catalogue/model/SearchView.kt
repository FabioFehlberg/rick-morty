package com.fehlves.rickmorty.features.catalogue.model

data class SearchView(
    override val id: Int = -1,
    val type: Int,
    val onSearchClick: (String) -> Unit
) : CatalogueView()