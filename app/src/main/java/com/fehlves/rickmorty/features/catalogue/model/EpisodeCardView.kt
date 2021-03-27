package com.fehlves.rickmorty.features.catalogue.model

data class EpisodeCardView(
    override val id: Int,
    val name: String,
    val episode: String,
    val airDate: String,
    var onClick: (() -> Unit)? = null
) : CatalogueView()