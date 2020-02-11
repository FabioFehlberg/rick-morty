package com.fehlves.rickmorty.data

import com.fehlves.rickmorty.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogueApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") pageNumber: Int,
        @Query("name") name: String = "",
        @Query("status") status: String = Constants.CharacterStatus.NON_SPECIFIED,
        @Query("gender") gender: String = Constants.CharacterGender.NON_SPECIFIED
    ): CharacterResultEntity

}