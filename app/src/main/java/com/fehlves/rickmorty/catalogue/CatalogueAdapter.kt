package com.fehlves.rickmorty.catalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.SearchView
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.main.model.CategoryView
import com.fehlves.rickmorty.main.model.TitleView
import kotlinx.android.synthetic.main.item_catalogue_search.view.*
import kotlinx.android.synthetic.main.item_character_card.view.*

class CatalogueAdapter : ListAdapter<BaseView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SEARCH_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_catalogue_search, parent, false)
                SearchViewHolder(view)
            }
            CHARACTER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_character_card, parent, false)
                CharacterViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchView -> SEARCH_TYPE
            is CharacterCardView -> CHARACTER_TYPE
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    class SearchViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            if (item is SearchView) {
                with(itemView) {
                    ivSearch.setOnClickListener { item.onSearchClick(etSearch.text.toString()) }
                    etSearch.setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            item.onSearchClick(etSearch.text.toString())
                            true
                        } else {
                            false
                        }
                    }
                }
            }
        }
    }

    class CharacterViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            val characterCardView = item as CharacterCardView
            with(itemView) {
                tvName.text = characterCardView.name
                tvGender.text = characterCardView.gender
                tvSpecies.text = characterCardView.specie
                tvStatus.text = characterCardView.status
                cvCharacterCard.setOnClickListener { characterCardView.onClick(characterCardView.url) }
            }
        }
    }

    //TODO continue to implement view holders for other search types

    companion object {
        const val SEARCH_TYPE = 0
        const val CHARACTER_TYPE = 1
        const val LOCATION_TYPE = 2
        const val EPISODE_TYPE = 3


        //TODO consider using an sub view(model) to set an ID and ease diff callback implementation

        val DIFF_CALLBACK: DiffUtil.ItemCallback<BaseView> =
            object : DiffUtil.ItemCallback<BaseView>() {
                override fun areItemsTheSame(oldItem: BaseView, newItem: BaseView): Boolean {
                    return if (oldItem is TitleView && newItem is TitleView) {
                        oldItem.icon == newItem.icon
                    } else if (oldItem is CategoryView && newItem is CategoryView) {
                        oldItem.icon == newItem.icon
                    } else {
                        false
                    }
                }

                override fun areContentsTheSame(oldItem: BaseView, newItem: BaseView): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}