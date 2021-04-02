package com.fehlves.rickmorty.detail

import androidx.lifecycle.Observer
import com.fehlves.rickmorty.BaseUnitTest
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.detail.DetailRepository
import com.fehlves.rickmorty.detail.DetailMockProvider.mockCharacterEntityList
import com.fehlves.rickmorty.detail.DetailMockProvider.mockCharacterEntitySingle
import com.fehlves.rickmorty.detail.DetailMockProvider.mockEpisodeEntityList
import com.fehlves.rickmorty.detail.DetailMockProvider.mockEpisodeEntitySingle
import com.fehlves.rickmorty.detail.DetailMockProvider.mockIdInt
import com.fehlves.rickmorty.detail.DetailMockProvider.mockIdString
import com.fehlves.rickmorty.features.detail.DetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailViewModelTest : BaseUnitTest() {

    @MockK
    private lateinit var detailRepository: DetailRepository

    private lateinit var viewModel: DetailViewModel

    @RelaxedMockK
    private lateinit var observerCharacters: Observer<List<CharacterEntity>?>

    @RelaxedMockK
    private lateinit var observerEpisodes: Observer<List<EpisodeEntity>?>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = DetailViewModel(detailRepository).apply {
            onCharacterListResult().observeForever(observerCharacters)
            onEpisodeListResult().observeForever(observerEpisodes)
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.apply {
            onCharacterListResult().removeObserver(observerCharacters)
            onEpisodeListResult().removeObserver(observerEpisodes)
        }
    }

    @Test
    fun `should load character list with success`() = runBlocking {
        coEvery { detailRepository.getCharacterList(mockIdString()) } returns BaseResult.Success(mockCharacterEntityList())

        viewModel.loadCharacterList(mockIdString())

        verify {
            observerCharacters.onChanged(mockCharacterEntityList())
        }
    }

    @Test
    fun `should load single character with success`() = runBlocking {
        coEvery { detailRepository.getCharacter(mockIdInt()) } returns BaseResult.Success(mockCharacterEntitySingle())

        viewModel.loadCharacter(mockIdInt())

        verify {
            observerCharacters.onChanged(listOf(mockCharacterEntitySingle()))
        }
    }

    @Test
    fun `should return error loading character list`() = runBlocking {
        coEvery { detailRepository.getCharacterList(mockIdString()) } returns BaseResult.Error(Throwable())

        viewModel.loadCharacterList(mockIdString())

        verify {
            observerCharacters.onChanged(null)
        }
    }

    @Test
    fun `should return error loading single character`() = runBlocking {
        coEvery { detailRepository.getCharacter(mockIdInt()) } returns BaseResult.Error(Throwable())

        viewModel.loadCharacter(mockIdInt())

        verify {
            observerCharacters.onChanged(null)
        }
    }

    @Test
    fun `should load episode list with success`() = runBlocking {
        coEvery { detailRepository.getEpisodeList(mockIdString()) } returns BaseResult.Success(mockEpisodeEntityList())

        viewModel.loadEpisodesList(mockIdString())

        verify {
            observerEpisodes.onChanged(mockEpisodeEntityList())
        }
    }

    @Test
    fun `should load single episode with success`() = runBlocking {
        coEvery { detailRepository.getEpisode(mockIdInt()) } returns BaseResult.Success(mockEpisodeEntitySingle())

        viewModel.loadEpisode(mockIdInt())

        verify {
            observerEpisodes.onChanged(listOf(mockEpisodeEntitySingle()))
        }
    }

    @Test
    fun `should return error loading episode list`() = runBlocking {
        coEvery { detailRepository.getEpisodeList(mockIdString()) } returns BaseResult.Error(Throwable())

        viewModel.loadEpisodesList(mockIdString())

        verify {
            observerEpisodes.onChanged(null)
        }
    }

    @Test
    fun `should return error loading single episode`() = runBlocking {
        coEvery { detailRepository.getEpisode(mockIdInt()) } returns BaseResult.Error(Throwable())

        viewModel.loadEpisode(mockIdInt())

        verify {
            observerEpisodes.onChanged(null)
        }
    }
}
