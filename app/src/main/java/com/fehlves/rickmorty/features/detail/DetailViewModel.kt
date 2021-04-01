package com.fehlves.rickmorty.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.common.BaseViewModel
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.detail.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val detailRepository: DetailRepository) : BaseViewModel() {

    private val onCharacterListResult = MutableLiveData<List<CharacterEntity>?>()
    private val onEpisodeListResult = MutableLiveData<List<EpisodeEntity>?>()

    fun onCharacterListResult(): LiveData<List<CharacterEntity>?> = onCharacterListResult
    fun onEpisodeListResult(): LiveData<List<EpisodeEntity>?> = onEpisodeListResult

    fun loadCharacterList(ids: String) {
        launch {
            when (val result = withContext(Dispatchers.IO) { detailRepository.getCharacterList(ids) }) {
                is BaseResult.Success -> onCharacterListResult.postValue(result.data)
                is BaseResult.Error -> onCharacterListResult.postValue(null)
            }
        }
    }

    fun loadEpisodesList(ids: String) {
        launch {
            when (val result = withContext(Dispatchers.IO) { detailRepository.getEpisodeList(ids) }) {
                is BaseResult.Success -> onEpisodeListResult.postValue(result.data)
                is BaseResult.Error -> onEpisodeListResult.postValue(null)
            }
        }
    }

    fun loadCharacter(id: Int) {
        launch {
            when (val result = withContext(Dispatchers.IO) { detailRepository.getCharacter(id) }) {
                is BaseResult.Success -> onCharacterListResult.postValue(listOf(result.data))
                is BaseResult.Error -> onCharacterListResult.postValue(null)
            }
        }
    }

    fun loadEpisode(id: Int) {
        launch {
            when (val result = withContext(Dispatchers.IO) { detailRepository.getEpisode(id) }) {
                is BaseResult.Success -> onEpisodeListResult.postValue(listOf(result.data))
                is BaseResult.Error -> onEpisodeListResult.postValue(null)
            }
        }
    }


}