package com.fehlves.rickmorty.detail

import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.NameUrl

object DetailMockProvider {

    fun mockIdInt() = 1
    fun mockIdString() = ""

    private val firstCharacterEntity = CharacterEntity(
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

    private val secondCharacterEntity = CharacterEntity(
        "2017-11-04T18:48:46.250Z",
        listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        ),
        "Male",
        2,
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        NameUrl("Earth (Replacement Dimension)", "https://rickandmortyapi.com/api/location/20"),
        "Rick Sanchez 2",
        NameUrl("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
        "Human",
        "Alive",
        "",
        "https://rickandmortyapi.com/api/character/1"
    )

    fun mockCharacterEntitySingle() = firstCharacterEntity
    fun mockCharacterEntityList() = listOf(firstCharacterEntity, secondCharacterEntity)

    private val firstEpisodeEntity = EpisodeEntity(
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

    private val secondEpisodeEntity = EpisodeEntity(
        "S01E02",
        2,
        "December 9, 2013",
        "Pilot 2",
        listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
            "https://rickandmortyapi.com/api/character/35",
            "https://rickandmortyapi.com/api/character/38"
        ),
        "2017-11-10T12:56:33.798Z",
        "https://rickandmortyapi.com/api/episode/1"
    )

    fun mockEpisodeEntitySingle() = firstEpisodeEntity
    fun mockEpisodeEntityList() = listOf(firstEpisodeEntity, secondEpisodeEntity)
}