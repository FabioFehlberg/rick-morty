package com.fehlves.rickmorty.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

class ViewHolderProvider {
    private val viewHolderFactories = hashMapOf<Int, Pair<Int, Any>>()

    fun provideViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val (layoutId: Int, f: Any) = viewHolderFactories[viewType]!!
        val viewHolderFactory = f as (View) -> RecyclerView.ViewHolder
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)
        return viewHolderFactory(view)
    }

    fun <T> registerViewHolderFactory(key: KClass<*>, layoutId: Int, viewHolderFactory: (View) -> T) {
        viewHolderFactories[key.hashCode()] = Pair(layoutId, viewHolderFactory)
    }
}