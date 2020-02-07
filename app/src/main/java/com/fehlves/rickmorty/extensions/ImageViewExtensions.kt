package com.fehlves.rickmorty.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(url: String) {
    Picasso.get().load(url).tag(context).into(this)
}