package com.fehlves.rickmorty.main.model

data class CategoryView (
    val icon: Int,
    val text: Int,
    val imageDescription: Int,
    val onClick: () -> Unit
) : MainView()