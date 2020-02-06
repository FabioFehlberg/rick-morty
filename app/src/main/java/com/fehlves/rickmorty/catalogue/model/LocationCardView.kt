package com.fehlves.rickmorty.catalogue.model

import com.fehlves.rickmorty.common.BaseView

data class LocationCardView(
    val name: String,
    val type: String,
    val dimension: String,
    val onClick: (String) -> Unit
) : BaseView()