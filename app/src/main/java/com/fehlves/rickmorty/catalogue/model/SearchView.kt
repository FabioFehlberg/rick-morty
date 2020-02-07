package com.fehlves.rickmorty.catalogue.model

data class SearchView(
    override val id: Int = -1,
    val onSearchClick: (String) -> Unit
) : CatalogueView()