package com.fehlves.rickmorty.catalogue.model

import com.fehlves.rickmorty.common.BaseView

data class CharacterCardView(
    val name: String,
    val specie: String,
    val gender: String,
    val status: String,
    val url: String,
    val onClick: (String) -> Unit
) : BaseView()