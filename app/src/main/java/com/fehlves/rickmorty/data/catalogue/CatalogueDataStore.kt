package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.toCharacterCardView
import com.fehlves.rickmorty.data.toEpisodeCardView
import com.fehlves.rickmorty.data.toLocationCardView

class CatalogueDataStore(private val catalogueApi: CatalogueApi) :
    CatalogueRepository {

    override suspend fun getCharacters(
        pageNumber: Int,
        characterName: String
    ): BaseResult<CharacterResultEntity> {
        return try {
            val result = catalogueApi.getCharacters(pageNumber, characterName)/*.results.map {
                it.toCharacterCardView()
            }*/
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
            val result = catalogueApi.getLocations(pageNumber, locationName)/*.results.map {
                it.toLocationCardView()
            }*/
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
            val result = catalogueApi.getEpisodes(pageNumber, episodeName, episode)/*.results.map {
                it.toEpisodeCardView()
            }*/
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

}