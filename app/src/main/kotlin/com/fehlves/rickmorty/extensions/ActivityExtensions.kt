package com.fehlves.rickmorty.extensions

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value
    else throw IllegalArgumentException("Couldn't find extra with key \"$key\" from type " + T::class.java.canonicalName)
}

inline fun <reified T> Activity.extra(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else default()
}

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }