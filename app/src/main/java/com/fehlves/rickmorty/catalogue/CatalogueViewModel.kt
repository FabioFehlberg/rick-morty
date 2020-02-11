package com.fehlves.rickmorty.catalogue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CatalogueDataStore
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CatalogueViewModel(private val catalogueRepository: CatalogueDataStore) : ViewModel(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val onShowLoading = MutableLiveData<Boolean>()
    private val onCharacterResult = MutableLiveData<List<CharacterCardView>>()
    private val onLocationResult = MutableLiveData<List<LocationCardView>>()

    fun onShowLoading(): LiveData<Boolean> = onShowLoading
    fun onCharacterResult(): LiveData<List<CharacterCardView>> = onCharacterResult
    fun onLocationResult(): LiveData<List<LocationCardView>> = onLocationResult

    fun loadCharacters(pageNumber: Int, characterName: String = "") {

        onShowLoading.postValue(true)

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    catalogueRepository.getCharacters(
                        pageNumber,
                        characterName
                    )
                }

            onShowLoading.postValue(false)

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

    fun loadLocations(pageNumber: Int, locationName: String = "") {

        onShowLoading.postValue(true)

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    catalogueRepository.getLocations(
                        pageNumber,
                        locationName
                    )
                }

            onShowLoading.postValue(false)

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

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}