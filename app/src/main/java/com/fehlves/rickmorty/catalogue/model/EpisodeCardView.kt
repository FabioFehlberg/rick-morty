package com.fehlves.rickmorty.catalogue.model

data class EpisodeCardView(
    override val id: Int,
    val name: String,
    val episode: String,
    val airDate: String,
    val onClick: () -> Unit
) : CatalogueView()