package com.fehlves.rickmorty.common

import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

interface AdapterItem {
    val viewType: Int
    fun bindViewHolder(viewHolder: RecyclerView.ViewHolder)
}

abstract class AdapterBase<T>(viewHolderClass: KClass<*>) : AdapterItem {
    override val viewType: Int = viewHolderClass.hashCode()
    abstract fun bindViewHolder(viewHolder: T)
    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        bindViewHolder(viewHolder as T)
    }
}