package com.fehlves.rickmorty.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.databinding.ItemMainCardBinding
import com.fehlves.rickmorty.databinding.ItemMainTitleBinding
import com.fehlves.rickmorty.extensions.getDrawableCompat
import com.fehlves.rickmorty.features.main.model.CategoryView
import com.fehlves.rickmorty.features.main.model.TitleView

class MainAdapter : ListAdapter<BaseView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE_TYPE -> {
                val binding = ItemMainTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }
            CATEGORY_TYPE -> {
                val binding = ItemMainCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CardViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TitleView -> TITLE_TYPE
            is CategoryView -> CATEGORY_TYPE
            else -> throw IllegalArgumentException("Invalid type of data at position $position")
        }
    }

    inner class TitleViewHolder(private val binding: ItemMainTitleBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val titleView = item as TitleView
            with(binding) {
                ivLogo.setImageDrawable(binding.root.context.getDrawableCompat(titleView.icon))
                ivLogo.contentDescription = binding.root.context.getString(titleView.imageDescription)
            }
        }
    }

    inner class CardViewHolder(private val binding: ItemMainCardBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseView) {
            val categoryView = item as CategoryView
            with(binding) {
                ivCategory.setImageDrawable(binding.root.context.getDrawableCompat(categoryView.icon))
                ivCategory.contentDescription = binding.root.context.getString(categoryView.imageDescription)
                tvCategory.text = binding.root.context.getString(categoryView.text)
                cvCategory.setOnClickListener { categoryView.onClick() }
            }
        }
    }

    companion object {
        const val TITLE_TYPE = 0
        const val CATEGORY_TYPE = 1

        val DIFF_CALLBACK: DiffUtil.ItemCallback<BaseView> = object : DiffUtil.ItemCallback<BaseView>() {
            override fun areItemsTheSame(oldItem: BaseView, newItem: BaseView): Boolean {
                return if (oldItem is TitleView && newItem is TitleView) oldItem.icon == newItem.icon
                else if (oldItem is CategoryView && newItem is CategoryView) oldItem.icon == newItem.icon
                else false
            }

            override fun areContentsTheSame(oldItem: BaseView, newItem: BaseView): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}