package com.fehlves.rickmorty.main

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.AdapterBase
import com.fehlves.rickmorty.common.AdapterItem
import com.fehlves.rickmorty.common.ViewHolderProvider

class MainAdapter(private val items: List<AdapterItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewHolderProvider = ViewHolderProvider()

    init {
        viewHolderProvider.apply {
            registerViewHolderFactory(
                TitleViewHolder::class,
                R.layout.item_main_title
            ) { itemView ->
                TitleViewHolder(itemView)
            }
            registerViewHolderFactory(
                CardViewHolder::class,
                R.layout.item_main_card
            ) { itemView ->
                CardViewHolder(itemView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return viewHolderProvider.provideViewHolder(viewGroup, viewType)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(viewHolder)
    }
}

class CardAdapterItem(
    private val icon: Drawable?,
    private val label: String,
    private val contentDescription: String,
    private val onClick: () -> Unit
) : AdapterBase<CardViewHolder>(CardViewHolder::class) {
    override fun bindViewHolder(viewHolder: CardViewHolder) {
        viewHolder.ivCategory.setImageDrawable(icon)
        viewHolder.ivCategory.contentDescription = contentDescription
        viewHolder.tvCategory.text = label
        viewHolder.cvCategory.setOnClickListener { onClick() }
    }
}

class TitleAdapterItem(
    private val icon: Drawable?,
    private val contentDescription: String,
    private val onClick: () -> Unit
) :
    AdapterBase<TitleViewHolder>(TitleViewHolder::class) {
    override fun bindViewHolder(viewHolder: TitleViewHolder) {
        viewHolder.ivLogo.setImageDrawable(icon)
        viewHolder.ivLogo.contentDescription = contentDescription
        viewHolder.itemView.setOnClickListener { onClick() }
    }
}