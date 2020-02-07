package com.fehlves.rickmorty.common

sealed class BaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : BaseResult<T>()
    class Error(val exception: Throwable) : BaseResult<Nothing>()
}