package com.fehlves.rickmorty.catalogue.model

import com.fehlves.rickmorty.common.BaseView

data class SearchView(
    val onSearchClick: () -> Unit
) : BaseView()