package com.fehlves.rickmorty.common

interface Constants {
    companion object {
        const val SEARCH_TYPE = 0
        const val CHARACTER_TYPE = 1
        const val LOCATION_TYPE = 2
        const val EPISODE_TYPE = 3
        const val LOADING_TYPE = 4
    }

    class CharacterStatus {
        companion object {
            const val ALIVE = "alive"
            const val DEAD = "dead"
            const val UNKNOWN = "unknown"
            const val NON_SPECIFIED = ""
        }
    }

    class CharacterGender {
        companion object {
            const val FEMALE = "female"
            const val MALE = "male"
            const val GENDERLESS = "genderless"
            const val UNKNOWN = "unknown"
            const val NON_SPECIFIED = ""
        }
    }
}