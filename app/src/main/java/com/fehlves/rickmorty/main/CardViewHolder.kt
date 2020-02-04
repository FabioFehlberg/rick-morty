package com.fehlves.rickmorty.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.fehlves.rickmorty.R

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivCategory: ImageView = itemView.findViewById(R.id.ivCategory)
    val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
    val cvCategory: CardView = itemView.findViewById(R.id.cvCategory)

}