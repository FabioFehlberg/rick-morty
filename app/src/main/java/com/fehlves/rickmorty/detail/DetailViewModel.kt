package com.fehlves.rickmorty.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity
import com.fehlves.rickmorty.data.detail.DetailDataStore
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailViewModel(private val detailRepository: DetailDataStore) : ViewModel(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val onCharacterResult = MutableLiveData<CharacterEntity>()
    private val onLocationResult = MutableLiveData<LocationEntity>()
    private val onEpisodeResult = MutableLiveData<EpisodeEntity>()

    fun onCharacterResult(): LiveData<CharacterEntity> = onCharacterResult
    fun onLocationResult(): LiveData<LocationEntity> = onLocationResult
    fun onEpisodeResult(): LiveData<EpisodeEntity> = onEpisodeResult

    fun loadDetails(id: Int, selectedType: Int) {
        when (selectedType) {
            CHARACTER_TYPE -> loadCharacter(id)
            LOCATION_TYPE -> loadLocations(id)
            EPISODE_TYPE -> loadEpisodes(id)
            else -> throw IllegalStateException("Incorrect type")
        }
    }

    private fun loadCharacter(id: Int) {

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    detailRepository.getCharacter(id)
                }

            when (result) {
                is BaseResult.Success -> {
                    Log.d(
                        "MY_LOG",
                        "deu certo uai"
                    )
                    onCharacterResult.postValue(result.data)
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }

    private fun loadLocations(id: Int) {

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    detailRepository.getLocation(id)
                }

            when (result) {
                is BaseResult.Success -> {
                    Log.d(
                        "MY_LOG",
                        "deu certo uai"
                    )
                    onLocationResult.postValue(result.data)
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }

    private fun loadEpisodes(id: Int) {

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    detailRepository.getEpisode(id)
                }

            when (result) {
                is BaseResult.Success -> {
                    Log.d(
                        "MY_LOG",
                        "deu certo uai"
                    )
                    onEpisodeResult.postValue(result.data)
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}