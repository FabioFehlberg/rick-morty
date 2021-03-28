package com.fehlves.rickmorty.features.detail.model

data class DetailInfoCardView(
    override val label: String,
    val iconUrl: String? = null,
    var onClick: (() -> Unit)? = null
) : BaseDetailInfoView()