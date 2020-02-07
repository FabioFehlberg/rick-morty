package com.fehlves.rickmorty.catalogue.model

import com.fehlves.rickmorty.common.BaseView

data class EpisodeCardView(
    val name: String,
    val episode: String,
    val airDate: String,
    val onClick: () -> Unit
) : BaseView()