package com.fehlves.rickmorty.data

import retrofit2.http.GET

interface CatalogueApi {

    @GET
    fun getCharacters(): List<CharacterEntity>

}