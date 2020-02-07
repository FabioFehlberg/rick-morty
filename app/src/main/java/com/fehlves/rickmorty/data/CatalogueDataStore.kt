package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.common.BaseResult
import java.lang.Exception

class CatalogueDataStore(private val catalogueApi: CatalogueApi): CatalogueRepository {
    override suspend fun getCharacters(): BaseResult<List<CharacterCardView>> {
        return try {
            val result = catalogueApi.getCharacters().results.map { it.toCharacterCardView() }
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }
}