package com.fehlves.rickmorty.catalogue

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.airbnb.lottie.LottieDrawable.REVERSE
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.*
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOADING_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.SEARCH_TYPE
import com.fehlves.rickmorty.databinding.*
import com.fehlves.rickmorty.extensions.loadImageFromUrl

class CatalogueAdapter : ListAdapter<CatalogueView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SEARCH_TYPE -> {
                val binding = ItemCatalogueSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SearchViewHolder(binding)
            }
            CHARACTER_TYPE -> {
                val binding = ItemCharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CharacterViewHolder(binding)
            }
            LOCATION_TYPE -> {
                val binding = ItemLocationCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LocationViewHolder(binding)
            }
            EPISODE_TYPE -> {
                val binding = ItemEpisodeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EpisodeViewHolder(binding)
            }
            LOADING_TYPE -> {
                val binding = ItemLoadingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
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
            is LoadingCardView -> LOADING_TYPE
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    class SearchViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            if (item is SearchView) {
                with(binding as ItemCatalogueSearchBinding) {
                    ivSearch.setOnClickListener { item.onSearchClick(etSearch.text.toString()) }
                    etSearch.setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            item.onSearchClick(etSearch.text.toString())
                            true
                        } else {
                            false
                        }
                    }

                    tiSearch.hint = when (item.type) {
                        CHARACTER_TYPE -> itemView.context.getString(R.string.catalogue_character_search_hint)
                        LOCATION_TYPE -> itemView.context.getString(R.string.catalogue_location_search_hint)
                        EPISODE_TYPE -> itemView.context.getString(R.string.catalogue_episode_search_hint)
                        else -> throw IllegalArgumentException("Invalid view type")
                    }
                }
            }
        }
    }

    class CharacterViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val characterCardView = item as CharacterCardView
            with(binding as ItemCharacterCardBinding) {
                tvName.text = characterCardView.name
                tvGender.text = itemView.context.getString(R.string.character_card_gender, characterCardView.gender)
                tvSpecies.text = itemView.context.getString(R.string.character_card_species, characterCardView.specie)
                tvStatus.text = itemView.context.getString(R.string.character_card_status, characterCardView.status)
                ivCharacter.loadImageFromUrl(characterCardView.image)
                cvCharacterCard.setOnClickListener { characterCardView.onClick?.invoke() }
            }
        }
    }

    class LocationViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val locationCardView = item as LocationCardView
            with(binding as ItemLocationCardBinding) {
                tvLocationName.text = locationCardView.name
                tvType.text = itemView.context.getString(R.string.location_card_type, locationCardView.type)
                tvDimension.text = itemView.context.getString(R.string.location_card_dimension, locationCardView.dimension)
                cvLocation.setOnClickListener { locationCardView.onClick?.invoke() }
            }
        }
    }

    class EpisodeViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val episodeCardView = item as EpisodeCardView
            with(binding as ItemEpisodeCardBinding) {
                tvEpisodeName.text = episodeCardView.name
                tvEpisode.text = episodeCardView.episode
                tvAirDate.text = itemView.context.getString(R.string.episode_card_air_date, episodeCardView.airDate)
                cvEpisode.setOnClickListener { episodeCardView.onClick?.invoke() }
            }
        }
    }

    class LoadingViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            with((binding as ItemLoadingCardBinding).laLoading) {
                setAnimation("loading.json")
                repeatMode = REVERSE
                repeatCount = INFINITE
                playAnimation()
            }
        }
    }

    companion object {

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