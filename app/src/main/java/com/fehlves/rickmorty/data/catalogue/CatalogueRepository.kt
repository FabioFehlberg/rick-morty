package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.common.BaseResult

interface CatalogueRepository {

    suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<CharacterResultEntity>

    suspend fun getLocations(
        pageNumber: Int,
        locationName: String
    ): BaseResult<LocationResultEntity>

    suspend fun getEpisodes(
        pageNumber: Int,
        episodeName: String,
        episode: String
    ): BaseResult<EpisodeResultEntity>

}