package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.common.BaseResult

interface CatalogueRepository {

    suspend fun getCharacters(): BaseResult<List<CharacterEntity>>

}