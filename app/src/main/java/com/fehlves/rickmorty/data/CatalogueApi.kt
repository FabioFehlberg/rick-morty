package com.fehlves.rickmorty.data

import retrofit2.http.GET

interface CatalogueApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResultEntity

}