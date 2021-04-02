package com.fehlves.rickmorty.features.detail.model

data class DetailInfoView(
    override val label: String,
    var isLoading: Boolean = false,
    var showRefresh: Boolean = false,
    val refreshAction: (() -> Unit)? = null
) : BaseDetailInfoView()