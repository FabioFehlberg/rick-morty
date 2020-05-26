package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.common.BaseResult

class CatalogueDataStore(private val catalogueApi: CatalogueApi) :
    CatalogueRepository {

    override suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<CharacterResultEntity> {
        return try {
            val result = catalogueApi.getCharacters(pageNumber, characterName)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getLocations(
        pageNumber: Int,
        locationName: String
    ): BaseResult<LocationResultEntity> {
        return try {
            val result = catalogueApi.getLocations(pageNumber, locationName)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getEpisodes(
        pageNumber: Int,
        episodeName: String,
        episode: String
    ): BaseResult<EpisodeResultEntity> {
        return try {
            val result = catalogueApi.getEpisodes(pageNumber, episodeName, episode)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

}