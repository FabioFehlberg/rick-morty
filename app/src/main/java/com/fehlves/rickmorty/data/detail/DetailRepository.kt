package com.fehlves.rickmorty.data.detail

import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity

interface DetailRepository {

    suspend fun getCharacter(id: Int): BaseResult<CharacterEntity>

    suspend fun getLocation(id: Int): BaseResult<LocationEntity>

    suspend fun getEpisode(id: Int): BaseResult<EpisodeEntity>

    suspend fun getCharacterList(ids: String): BaseResult<List<CharacterEntity>>

    suspend fun getLocationList(ids: String): BaseResult<List<LocationEntity>>

    suspend fun getEpisodeList(ids: String): BaseResult<List<EpisodeEntity>>

}