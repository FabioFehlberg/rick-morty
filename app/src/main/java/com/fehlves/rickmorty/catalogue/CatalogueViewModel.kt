package com.fehlves.rickmorty.catalogue

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CatalogueDataStore
import com.fehlves.rickmorty.data.CharacterEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CatalogueViewModel(private val catalogueRepository: CatalogueDataStore) : ViewModel(),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val onShowLoading = MutableLiveData<Boolean>()
    private val onCharacterResult = MutableLiveData<List<CharacterEntity>>()

    fun onCharacterResult(): LiveData<List<CharacterEntity>> = onCharacterResult
    fun onShowLoading(): LiveData<Boolean> = onShowLoading

    fun loadCharacters() {

        onShowLoading.value = true

        launch {

            val result = withContext(Dispatchers.IO) { catalogueRepository.getCharacters() }

            onShowLoading.value = false

            when (result) {
                is BaseResult.Success -> onCharacterResult.value = result.data
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