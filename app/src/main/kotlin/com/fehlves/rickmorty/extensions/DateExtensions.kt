package com.fehlves.rickmorty.extensions

import java.text.SimpleDateFormat
import java.util.*

val String.Companion.DATE_TIME_ZONE_FORMAT: String
    get() = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

val String.Companion.DATE_VIEW_FORMAT: String
    get() = "MM/dd/yyyy - HH:mm:ss"

fun String.formatDate(from: String = String.DATE_TIME_ZONE_FORMAT, to: String = String.DATE_VIEW_FORMAT): String =
    try {
        SimpleDateFormat(from, Locale.US).parse(this)?.let {
            SimpleDateFormat(to, Locale.US).format(it)
        } ?: ""
    } catch (exception: Exception) {
        ""
    }