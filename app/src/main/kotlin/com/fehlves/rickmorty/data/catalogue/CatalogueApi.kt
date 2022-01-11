package com.fehlves.rickmorty.data.catalogue

import com.fehlves.rickmorty.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogueApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("gender") gender: String = ""
    ): CharacterResultEntity

    @GET("location")
    suspend fun getLocations(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    ): LocationResultEntity

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("episode") type: String = ""
    ): EpisodeResultEntity

}