package com.fehlves.rickmorty.catalogue.model

import com.fehlves.rickmorty.common.BaseView

abstract class CatalogueView : BaseView() {
    abstract val id: Int
}