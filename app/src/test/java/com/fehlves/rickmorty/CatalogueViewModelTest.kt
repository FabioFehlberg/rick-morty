package com.fehlves.rickmorty

import androidx.lifecycle.Observer
import com.fehlves.rickmorty.CatalogueMockProvider.mockCharacterName
import com.fehlves.rickmorty.CatalogueMockProvider.mockCharacterResultAsCardView
import com.fehlves.rickmorty.CatalogueMockProvider.mockCharacterResultEntity
import com.fehlves.rickmorty.CatalogueMockProvider.mockEpisodeResultAsCardView
import com.fehlves.rickmorty.CatalogueMockProvider.mockEpisodeResultEntity
import com.fehlves.rickmorty.CatalogueMockProvider.mockLocationResultAsCardView
import com.fehlves.rickmorty.CatalogueMockProvider.mockLocationResultEntity
import com.fehlves.rickmorty.CatalogueMockProvider.mockPageNumber
import com.fehlves.rickmorty.common.BaseResult
import com.fehlves.rickmorty.common.Constants
import com.fehlves.rickmorty.data.catalogue.CatalogueRepository
import com.fehlves.rickmorty.features.catalogue.CatalogueViewModel
import com.fehlves.rickmorty.features.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.features.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.features.catalogue.model.LocationCardView
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifySequence
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
class CatalogueViewModelTest : BaseUnitTest() {

    @MockK
    private lateinit var catalogueRepository: CatalogueRepository

    private lateinit var viewModel: CatalogueViewModel

    @RelaxedMockK
    private lateinit var observerShowLoading: Observer<Boolean>

    @RelaxedMockK
    private lateinit var observerLoadMoreError: Observer<Unit?>

    @RelaxedMockK
    private lateinit var observerCharacters: Observer<List<CharacterCardView>>

    @RelaxedMockK
    private lateinit var observerLocations: Observer<List<LocationCardView>>

    @RelaxedMockK
    private lateinit var observerEpisodes: Observer<List<EpisodeCardView>>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = CatalogueViewModel(catalogueRepository).apply {
            onShowLoading().observeForever(observerShowLoading)
            onLoadMoreError().observeForever(observerLoadMoreError)
            onCharacterResult().observeForever(observerCharacters)
            onLocationResult().observeForever(observerLocations)
            onEpisodeResult().observeForever(observerEpisodes)
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.apply {
            onShowLoading().removeObserver(observerShowLoading)
            onLoadMoreError().removeObserver(observerLoadMoreError)
            onCharacterResult().removeObserver(observerCharacters)
            onLocationResult().removeObserver(observerLocations)
            onEpisodeResult().removeObserver(observerEpisodes)
        }
    }

    @Test
    fun `should load character with success`() = runBlocking {
        coEvery { catalogueRepository.getCharacters(mockPageNumber(), mockCharacterName()) } returns BaseResult.Success(
            mockCharacterResultEntity()
        )

        viewModel.setSelectedType(Constants.CHARACTER_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerCharacters.onChanged(mockCharacterResultAsCardView())
            observerShowLoading.onChanged(false)
        }
    }

    @Test
    fun `should return error loading character`() = runBlocking {
        coEvery { catalogueRepository.getCharacters(mockPageNumber(), mockCharacterName()) } returns BaseResult.Error(Throwable())

        viewModel.setSelectedType(Constants.CHARACTER_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerLoadMoreError.onChanged(null)
            observerShowLoading.onChanged(false)
        }
    }

    @Test
    fun `should load location with success`() = runBlocking {
        coEvery { catalogueRepository.getLocations(mockPageNumber(), mockCharacterName()) } returns BaseResult.Success(
            mockLocationResultEntity()
        )

        viewModel.setSelectedType(Constants.LOCATION_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerLocations.onChanged(mockLocationResultAsCardView())
            observerShowLoading.onChanged(false)
        }
    }

    @Test
    fun `should return error loading location`() = runBlocking {
        coEvery { catalogueRepository.getLocations(mockPageNumber(), mockCharacterName()) } returns BaseResult.Error(Throwable())

        viewModel.setSelectedType(Constants.LOCATION_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerLoadMoreError.onChanged(null)
            observerShowLoading.onChanged(false)
        }
    }

    @Test
    fun `should load episode with success`() = runBlocking {
        coEvery { catalogueRepository.getEpisodes(mockPageNumber(), mockCharacterName(), mockCharacterName()) } returns BaseResult.Success(
            mockEpisodeResultEntity()
        )

        viewModel.setSelectedType(Constants.EPISODE_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerEpisodes.onChanged(mockEpisodeResultAsCardView())
            observerShowLoading.onChanged(false)
        }
    }

    @Test
    fun `should return error loading episode`() = runBlocking {
        coEvery { catalogueRepository.getEpisodes(mockPageNumber(), mockCharacterName(), mockCharacterName()) } returns BaseResult.Error(
            Throwable()
        )

        viewModel.setSelectedType(Constants.EPISODE_TYPE)
        viewModel.loadMoreItems(mockPageNumber(), mockCharacterName())

        verifySequence {
            observerShowLoading.onChanged(true)
            observerLoadMoreError.onChanged(null)
            observerShowLoading.onChanged(false)
        }
    }
}
