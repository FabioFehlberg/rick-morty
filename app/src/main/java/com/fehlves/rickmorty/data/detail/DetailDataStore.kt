package com.fehlves.rickmorty.data.detail

import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity

class DetailDataStore(private val detailApi: DetailApi) : DetailRepository {

    override suspend fun getCharacter(id: Int): BaseResult<CharacterEntity> {
        return try {
            val result = detailApi.getCharacter(id)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getLocation(id: Int): BaseResult<LocationEntity> {
        return try {
            val result = detailApi.getLocation(id)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getEpisode(id: Int): BaseResult<EpisodeEntity> {
        return try {
            val result = detailApi.getEpisode(id)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getCharacterList(ids: String): BaseResult<List<CharacterEntity>> {
        return try {
            val result = detailApi.getCharacterList(ids)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getLocationList(ids: String): BaseResult<List<LocationEntity>> {
        return try {
            val result = detailApi.getLocationList(ids)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

    override suspend fun getEpisodeList(ids: String): BaseResult<List<EpisodeEntity>> {
        return try {
            val result = detailApi.getEpisodeList(ids)
            BaseResult.Success(result)
        } catch (ex: Exception) {
            BaseResult.Error(ex)
        }
    }

}