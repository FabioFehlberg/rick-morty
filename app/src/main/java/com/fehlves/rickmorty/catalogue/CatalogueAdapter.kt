package com.fehlves.rickmorty.catalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.*
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import kotlinx.android.synthetic.main.item_catalogue_search.view.*
import kotlinx.android.synthetic.main.item_character_card.view.*
import kotlinx.android.synthetic.main.item_episode_card.view.*
import kotlinx.android.synthetic.main.item_location_card.view.*

class CatalogueAdapter : ListAdapter<CatalogueView, BaseViewHolder>(DIFF_CALLBACK) {

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
            LOCATION_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_location_card, parent, false)
                LocationViewHolder(view)
            }
            EPISODE_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_episode_card, parent, false)
                EpisodeViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchView -> SEARCH_TYPE
            is CharacterCardView -> CHARACTER_TYPE
            is LocationCardView -> LOCATION_TYPE
            is EpisodeCardView -> EPISODE_TYPE
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
                cvCharacterCard.setOnClickListener { characterCardView.onClick() }
            }
        }
    }

    class LocationViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            val locationCardView = item as LocationCardView
            with(itemView) {
                tvLocationName.text = locationCardView.name
                tvType.text = locationCardView.type
                tvDimension.text = locationCardView.dimension
                cvLocation.setOnClickListener { locationCardView.onClick() }
            }
        }
    }

    class EpisodeViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            val episodeCardView = item as EpisodeCardView
            with(itemView) {
                tvEpisodeName.text = episodeCardView.name
                tvEpisode.text = episodeCardView.episode
                tvAirDate.text = episodeCardView.airDate
                cvEpisode.setOnClickListener { episodeCardView.onClick() }
            }
        }
    }

    companion object {
        const val SEARCH_TYPE = 0
        const val CHARACTER_TYPE = 1
        const val LOCATION_TYPE = 2
        const val EPISODE_TYPE = 3

        val DIFF_CALLBACK: DiffUtil.ItemCallback<CatalogueView> =
            object : DiffUtil.ItemCallback<CatalogueView>() {
                override fun areItemsTheSame(
                    oldItem: CatalogueView,
                    newItem: CatalogueView
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CatalogueView,
                    newItem: CatalogueView
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}