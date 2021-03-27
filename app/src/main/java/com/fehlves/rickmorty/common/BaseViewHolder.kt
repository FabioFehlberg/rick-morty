package com.fehlves.rickmorty.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: BaseView)
}