package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseResult

class CatalogueDataStore(private val catalogueApi: CatalogueApi) :
    CatalogueRepository {

    override suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<List<CharacterCardView>> {
        return try {
            val result = catalogueApi.getCharacters(pageNumber, characterName).results.map {
                it.toCharacterCardView()
            }
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getLocations(
        pageNumber: Int,
        locationName: String
    ): BaseResult<List<LocationCardView>> {
        return try {
            val result = catalogueApi.getLocations(pageNumber, locationName).results.map {
                it.toLocationCardView()
            }
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getEpisodes(
        pageNumber: Int,
        episodeName: String,
        episode: String
    ): BaseResult<List<EpisodeCardView>> {
        return try {
            val result = catalogueApi.getEpisodes(pageNumber, episodeName, episode).results.map {
                it.toEpisodeCardView()
            }
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

}