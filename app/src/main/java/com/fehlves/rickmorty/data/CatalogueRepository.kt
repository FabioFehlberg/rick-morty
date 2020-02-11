package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseResult

interface CatalogueRepository {

    suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<List<CharacterCardView>>

    suspend fun getLocations(
        pageNumber: Int,
        locationName: String
    ): BaseResult<List<LocationCardView>>

}