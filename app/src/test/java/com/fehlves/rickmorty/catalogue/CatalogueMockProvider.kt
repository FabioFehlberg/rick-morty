package com.fehlves.rickmorty.catalogue

import com.fehlves.rickmorty.data.*
import com.fehlves.rickmorty.data.catalogue.CharacterResultEntity
import com.fehlves.rickmorty.data.catalogue.EpisodeResultEntity
import com.fehlves.rickmorty.data.catalogue.InfoEntity
import com.fehlves.rickmorty.data.catalogue.LocationResultEntity

object CatalogueMockProvider {

    fun mockPageNumber() = 1
    fun mockCharacterName() = ""

    private val characterEntity = CharacterEntity(
        "2017-11-04T18:48:46.250Z",
        listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        ),
        "Male",
        1,
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        NameUrl("Earth (Replacement Dimension)", "https://rickandmortyapi.com/api/location/20"),
        "Rick Sanchez",
        NameUrl("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
        "Human",
        "Alive",
        "",
        "https://rickandmortyapi.com/api/character/1"
    )

    fun mockCharacterResultEntity() = CharacterResultEntity(
        InfoEntity(671, 34, "https://rickandmortyapi.com/api/character?page=2", null),
        listOf(characterEntity)
    )

    fun mockCharacterResultAsCardView() = mockCharacterResultEntity().results.map { it.toCharacterCardView() }

    private val locationEntity = LocationEntity(
        "2017-11-10T12:42:04.162Z",
        1,
        listOf(
            "https://rickandmortyapi.com/api/character/38",
            "https://rickandmortyapi.com/api/character/45",
            "https://rickandmortyapi.com/api/character/71",
            "https://rickandmortyapi.com/api/character/82"
        ),
        "Earth (C-137)",
        "Dimension C-137",
        "Planet",
        "https://rickandmortyapi.com/api/location/1"
    )

    fun mockLocationResultEntity() = LocationResultEntity(
        InfoEntity(108, 6, "https://rickandmortyapi.com/api/character?page=2", null),
        listOf(locationEntity)
    )

    fun mockLocationResultAsCardView() = mockLocationResultEntity().results.map { it.toLocationCardView() }

    private val episodeEntity = EpisodeEntity(
        "S01E01",
        1,
        "December 2, 2013",
        "Pilot",
        listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
            "https://rickandmortyapi.com/api/character/35",
            "https://rickandmortyapi.com/api/character/38"
        ),
        "2017-11-10T12:56:33.798Z",
        "https://rickandmortyapi.com/api/episode/1"
    )

    fun mockEpisodeResultEntity() = EpisodeResultEntity(
        InfoEntity(41, 3, "https://rickandmortyapi.com/api/episode?page=2", null),
        listOf(episodeEntity)
    )

    fun mockEpisodeResultAsCardView() = mockEpisodeResultEntity().results.map { it.toEpisodeCardView() }
}