package com.fehlves.rickmorty.features.catalogue.model

import com.fehlves.rickmorty.common.BaseView

abstract class CatalogueView : BaseView() {
    abstract val id: Int
}