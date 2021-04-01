package com.fehlves.rickmorty.data.detail

import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") pageNumber: Int): CharacterEntity

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") pageNumber: Int): EpisodeEntity

    @GET("character/{id}")
    suspend fun getCharacterList(@Path("id") idList: String): List<CharacterEntity>

    @GET("episode/{id}")
    suspend fun getEpisodeList(@Path("id") idList: String): List<EpisodeEntity>

}