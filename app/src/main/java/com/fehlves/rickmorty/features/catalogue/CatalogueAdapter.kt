package com.fehlves.rickmorty.features.catalogue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.airbnb.lottie.LottieDrawable
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.ERROR_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOADING_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.databinding.*
import com.fehlves.rickmorty.extensions.loadImageFromUrl
import com.fehlves.rickmorty.features.catalogue.model.*

class CatalogueAdapter : ListAdapter<CatalogueView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
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
            ERROR_TYPE -> {
                val binding = ItemErrorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ErrorViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharacterCardView -> CHARACTER_TYPE
            is LocationCardView -> LOCATION_TYPE
            is EpisodeCardView -> EPISODE_TYPE
            is LoadingCardView -> LOADING_TYPE
            is ErrorCardView -> ERROR_TYPE
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val characterCardView = item as CharacterCardView
            with(binding) {
                tvName.text = characterCardView.name
                tvGender.text = itemView.context.getString(R.string.character_card_gender, characterCardView.gender)
                tvSpecies.text = itemView.context.getString(R.string.character_card_species, characterCardView.specie)
                tvStatus.text = itemView.context.getString(R.string.character_card_status, characterCardView.status)
                ivCharacter.loadImageFromUrl(characterCardView.image)
                cvCharacterCard.setOnClickListener { characterCardView.onClick?.invoke() }
            }
        }
    }

    inner class LocationViewHolder(private val binding: ItemLocationCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val locationCardView = item as LocationCardView
            with(binding) {
                tvLocationName.text = locationCardView.name
                tvType.text = itemView.context.getString(R.string.location_card_type, locationCardView.type)
                tvDimension.text = itemView.context.getString(R.string.location_card_dimension, locationCardView.dimension)
                cvLocation.setOnClickListener { locationCardView.onClick?.invoke() }
            }
        }
    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val episodeCardView = item as EpisodeCardView
            with(binding) {
                tvEpisodeName.text = episodeCardView.name
                tvEpisode.text = episodeCardView.episode
                tvAirDate.text = itemView.context.getString(R.string.episode_card_air_date, episodeCardView.airDate)
                cvEpisode.setOnClickListener { episodeCardView.onClick?.invoke() }
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            binding.laLoading.repeatCount = LottieDrawable.INFINITE
        }
    }

    inner class ErrorViewHolder(private val binding: ItemErrorCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            binding.cvError.setOnClickListener {
                (item as ErrorCardView).action.invoke()
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