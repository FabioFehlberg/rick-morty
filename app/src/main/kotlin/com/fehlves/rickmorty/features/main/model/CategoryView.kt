package com.fehlves.rickmorty.features.main.model

import com.fehlves.rickmorty.common.BaseView

data class CategoryView (
    val icon: Int,
    val text: Int,
    val imageDescription: Int,
    val onClick: () -> Unit
) : BaseView()