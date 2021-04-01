package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.common.BaseResult

class CatalogueDataStore(private val catalogueApi: CatalogueApi) :
    CatalogueRepository {

    override suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<CharacterResultEntity> {
        return try {
            BaseResult.Success(catalogueApi.getCharacters(pageNumber, characterName)).also {
                it.logResult()
            }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also {
                it.logResult()
            }
        }
    }

    override suspend fun getLocations(
        pageNumber: Int,
        locationName: String
    ): BaseResult<LocationResultEntity> {
        return try {
            BaseResult.Success(catalogueApi.getLocations(pageNumber, locationName)).also {
                it.logResult()
            }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also {
                it.logResult()
            }
        }
    }

    override suspend fun getEpisodes(
        pageNumber: Int,
        episodeName: String,
        episode: String
    ): BaseResult<EpisodeResultEntity> {
        return try {
            BaseResult.Success(catalogueApi.getEpisodes(pageNumber, episodeName, episode)).also {
                it.logResult()
            }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also {
                it.logResult()
            }
        }
    }

}