package com.fehlves.rickmorty.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.databinding.DetailInfoBinding
import com.fehlves.rickmorty.databinding.DetailInfoCardBinding
import com.fehlves.rickmorty.extensions.loadImageFromUrl
import com.fehlves.rickmorty.features.detail.model.BaseDetailInfoView
import com.fehlves.rickmorty.features.detail.model.DetailInfoCardView
import com.fehlves.rickmorty.features.detail.model.DetailInfoView

class DetailAdapter : ListAdapter<BaseDetailInfoView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            DETAIL_INFO -> {
                val binding = DetailInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DetailInfoViewHolder(binding)
            }
            DETAIL_INFO_CARD -> {
                val binding = DetailInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DetailInfoCardViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DetailInfoView -> DETAIL_INFO
            is DetailInfoCardView -> DETAIL_INFO_CARD
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    inner class DetailInfoViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val detailInfoView = item as DetailInfoView
            with(binding as DetailInfoBinding) {
                tvLabel.text = detailInfoView.label
                pbInfoLoading.visibility = if (detailInfoView.isLoading) View.VISIBLE else View.GONE
                ibRefresh.visibility = if (detailInfoView.showRefresh) View.VISIBLE else View.GONE
                ibRefresh.setOnClickListener {
                    pbInfoLoading.visibility = View.VISIBLE
                    ibRefresh.visibility = View.GONE
                    detailInfoView.refreshAction?.invoke()
                }
            }
        }
    }

    inner class DetailInfoCardViewHolder(binding: ViewBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val detailInfoCardView = item as DetailInfoCardView
            with(binding as DetailInfoCardBinding) {
                detailInfoCardView.iconUrl?.let { ivIcon.loadImageFromUrl(it) } ?: run { ivIcon.visibility = View.GONE }
                tvLabel.text = detailInfoCardView.label
                cvDetailInfoCard.setOnClickListener { detailInfoCardView.onClick?.invoke() }
            }
        }
    }

    companion object {
        private const val DETAIL_INFO = 0
        private const val DETAIL_INFO_CARD = 1

        val DIFF_CALLBACK: DiffUtil.ItemCallback<BaseDetailInfoView> =
            object : DiffUtil.ItemCallback<BaseDetailInfoView>() {
                override fun areItemsTheSame(
                    oldItem: BaseDetailInfoView,
                    newItem: BaseDetailInfoView
                ): Boolean {
                    return oldItem.label == newItem.label
                }

                override fun areContentsTheSame(
                    oldItem: BaseDetailInfoView,
                    newItem: BaseDetailInfoView
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}