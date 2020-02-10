package com.fehlves.rickmorty.catalogue.model

data class LoadingCardView(
    override val id: Int = Int.MAX_VALUE
) : CatalogueView()