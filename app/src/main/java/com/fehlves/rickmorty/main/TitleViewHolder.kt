package com.fehlves.rickmorty.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fehlves.rickmorty.R

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivLogo: ImageView = itemView.findViewById(R.id.ivLogo)
}