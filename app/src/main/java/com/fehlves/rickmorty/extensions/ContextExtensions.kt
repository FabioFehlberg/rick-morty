package com.fehlves.rickmorty.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)