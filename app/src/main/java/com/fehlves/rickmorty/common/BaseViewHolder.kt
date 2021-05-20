package com.fehlves.rickmorty.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun bind(item: BaseView)
}