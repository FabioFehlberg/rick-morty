package com.fehlves.rickmorty.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseView
import com.fehlves.rickmorty.common.BaseViewHolder
import com.fehlves.rickmorty.main.model.CategoryView
import com.fehlves.rickmorty.main.model.TitleView
import kotlinx.android.synthetic.main.item_main_card.view.*
import kotlinx.android.synthetic.main.item_main_title.view.*

class MainAdapter : ListAdapter<BaseView, BaseViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_title, parent, false)
                TitleViewHolder(view)
            }
            CATEGORY_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main_card, parent, false)
                CardViewHolder(view)
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

    class TitleViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            val titleView = item as TitleView
            with (itemView) {
                ivLogo.setImageDrawable(context.getDrawable(titleView.icon))
                ivLogo.contentDescription = context.getString(titleView.imageDescription)
            }
        }
    }

    class CardViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: BaseView) {
            val categoryView = item as CategoryView
            with (itemView) {
                ivCategory.setImageDrawable(context.getDrawable(categoryView.icon))
                ivCategory.contentDescription = context.getString(categoryView.imageDescription)
                tvCategory.text = context.getString(categoryView.text)
                cvCategory.setOnClickListener { categoryView.onClick() }
            }
        }
    }

    companion object {
        const val TITLE_TYPE = 0
        const val CATEGORY_TYPE = 1

        val DIFF_CALLBACK: DiffUtil.ItemCallback<BaseView> = object : DiffUtil.ItemCallback<BaseView>() {
            override fun areItemsTheSame(oldUser: BaseView, newUser: BaseView): Boolean {
                return if (oldUser is TitleView && newUser is TitleView) {
                    oldUser.icon == newUser.icon
                } else if (oldUser is CategoryView && newUser is CategoryView) {
                    oldUser.icon == newUser.icon
                } else {
                    false
                }
            }

            override fun areContentsTheSame(oldUser: BaseView, newUser: BaseView): Boolean {
                return oldUser.equals(newUser)
            }
        }
    }
}