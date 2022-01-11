package com.fehlves.rickmorty.common

import android.util.Log

sealed class BaseResult<out T : Any> : LogResult {
    class Success<out T : Any>(val data: T) : BaseResult<T>() {
        override fun logResult() {
            Log.d(
                "MY_LOG",
                data.toString()
            )
        }
    }

    class Error(val exception: Throwable) : BaseResult<Nothing>() {
        override fun logResult() {
            Log.d(
                "MY_LOG",
                exception.message ?: "Exception message is null"
            )
        }
    }
}