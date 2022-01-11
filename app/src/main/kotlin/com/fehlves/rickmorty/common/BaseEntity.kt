package com.fehlves.rickmorty.common

import android.os.Parcelable

abstract class BaseEntity : Parcelable {
    abstract val id: Int
}