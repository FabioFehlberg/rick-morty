package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.data.CharacterEntity

data class CharacterResultEntity(
    val info: InfoEntity,
    val results: List<CharacterEntity>
)