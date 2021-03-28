package com.fehlves.rickmorty.data.detail

import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") pageNumber: Int): CharacterEntity

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") pageNumber: Int): LocationEntity

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") pageNumber: Int): EpisodeEntity

    @GET("character/{id}")
    suspend fun getCharacterList(@Path("id") idList: String): List<CharacterEntity>

    @GET("location/{id}")
    suspend fun getLocationList(@Path("id") idList: String): List<LocationEntity>

    @GET("episode/{id}")
    suspend fun getEpisodeList(@Path("id") idList: String): List<EpisodeEntity>

}