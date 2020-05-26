package com.fehlves.rickmorty.catalogue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.common.BaseEntity
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.common.BaseViewModel
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.data.catalogue.CatalogueDataStore
import com.fehlves.rickmorty.data.toCharacterCardView
import com.fehlves.rickmorty.data.toEpisodeCardView
import com.fehlves.rickmorty.data.toLocationCardView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogueViewModel(private val catalogueRepository: CatalogueDataStore) : BaseViewModel() {

    private val itemsEntity = arrayListOf<BaseEntity>()
    private var selectedType = Int.MIN_VALUE

    private val onShowLoading = MutableLiveData<Boolean>()
    private val onCharacterResult = MutableLiveData<List<CharacterCardView>>()
    private val onLocationResult = MutableLiveData<List<LocationCardView>>()
    private val onEpisodeResult = MutableLiveData<List<EpisodeCardView>>()

    fun onShowLoading(): LiveData<Boolean> = onShowLoading
    fun onCharacterResult(): LiveData<List<CharacterCardView>> = onCharacterResult
    fun onLocationResult(): LiveData<List<LocationCardView>> = onLocationResult
    fun onEpisodeResult(): LiveData<List<EpisodeCardView>> = onEpisodeResult

    fun setSelectedType(type: Int) {
        selectedType = type
    }

    fun resetListOfItems() {
        itemsEntity.clear()
    }

    fun getItemEntityById(id: Int): BaseEntity? {
        return itemsEntity.find { it.id == id }
    }

    fun loadMoreItems(page: Int, searchInput: String) {
        when (selectedType) {
            CHARACTER_TYPE -> loadCharacters(page, searchInput)
            LOCATION_TYPE -> loadLocations(page, searchInput)
            EPISODE_TYPE -> loadEpisodes(page, searchInput)
            else -> throw IllegalStateException("Incorrect type")
        }
    }

    private fun loadCharacters(pageNumber: Int, characterName: String = "") {

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
                    val items = result.data.results
                    itemsEntity.addAll(items)
                    onCharacterResult.postValue(items.map { it.toCharacterCardView() })
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }

    private fun loadLocations(pageNumber: Int, locationName: String = "") {

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
                    val items = result.data.results
                    itemsEntity.addAll(items)
                    onLocationResult.postValue(items.map { it.toLocationCardView() })
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }

    private fun loadEpisodes(pageNumber: Int, episodeName: String = "", episode: String = "") {

        onShowLoading.postValue(true)

        launch {

            val result =
                withContext(Dispatchers.IO) {
                    catalogueRepository.getEpisodes(
                        pageNumber,
                        episodeName,
                        episode
                    )
                }

            onShowLoading.postValue(false)

            when (result) {
                is BaseResult.Success -> {
                    Log.d(
                        "MY_LOG",
                        "deu certo uai"
                    )
                    val items = result.data.results
                    itemsEntity.addAll(items)
                    onEpisodeResult.postValue(items.map { it.toEpisodeCardView() })
                }
                is BaseResult.Error -> Log.d(
                    "MY_LOG",
                    result.exception.message ?: "message is null"
                )
            }
        }
    }
}