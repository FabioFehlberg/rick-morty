package com.fehlves.rickmorty.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogueApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Int): CharacterResultEntity

}