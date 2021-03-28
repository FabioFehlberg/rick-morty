package com.fehlves.rickmorty.features.detail.model

import com.fehlves.rickmorty.common.BaseView

abstract class BaseDetailInfoView: BaseView() {
    abstract val label: String
}