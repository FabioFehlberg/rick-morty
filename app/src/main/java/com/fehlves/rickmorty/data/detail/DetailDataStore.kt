package com.fehlves.rickmorty.data.detail

import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity

class DetailDataStore(private val detailApi: DetailApi) : DetailRepository {

    override suspend fun getCharacter(id: Int): BaseResult<CharacterEntity> {
        return try {
            BaseResult.Success(detailApi.getCharacter(id)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

    override suspend fun getLocation(id: Int): BaseResult<LocationEntity> {
        return try {
            BaseResult.Success(detailApi.getLocation(id)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

    override suspend fun getEpisode(id: Int): BaseResult<EpisodeEntity> {
        return try {
            BaseResult.Success(detailApi.getEpisode(id)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

    override suspend fun getCharacterList(ids: String): BaseResult<List<CharacterEntity>> {
        return try {
            BaseResult.Success(detailApi.getCharacterList(ids)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

    override suspend fun getLocationList(ids: String): BaseResult<List<LocationEntity>> {
        return try {
            BaseResult.Success(detailApi.getLocationList(ids)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

    override suspend fun getEpisodeList(ids: String): BaseResult<List<EpisodeEntity>> {
        return try {
            BaseResult.Success(detailApi.getEpisodeList(ids)).also { it.logResult() }
        } catch (ex: Exception) {
            BaseResult.Error(ex).also { it.logResult() }
        }
    }

}