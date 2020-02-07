package com.fehlves.rickmorty.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.*
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.extensions.extra
import com.fehlves.rickmorty.extensions.observeNotNull
import kotlinx.android.synthetic.main.activity_catalogue.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogueActivity : BaseActivity() {

    private val selectedType by extra<Int>(ARG_SELECTED_TYPE)

    private val viewModel: CatalogueViewModel by viewModel()

    private val itemsList: ArrayList<CatalogueView> = arrayListOf()

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearchView()

        viewModel.onCharacterResult().observeNotNull(this) {
            //setUpList()
        }

        setupViews()
    }

    private fun setupSearchView() {
        val searchView = SearchView(
            type = selectedType
        ) {
            //TODO call api
        }

        itemsList += searchView

        rvCatalogue.layoutManager = LinearLayoutManager(this)
        rvCatalogue.adapter = CatalogueAdapter().apply { submitList(itemsList) }
    }

    private fun setUpList(items: List<CatalogueView>) {
        itemsList += items
    }

    private fun setupViews() {


        val characterCardView = CharacterCardView(
            id = 0,
            name = "Rick",
            specie = "Human",
            gender = "Unknown",
            status = "Alive",
            image = "link"
        ) {
            //TODO open character info
        }

        val locationCardView = LocationCardView(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "C89-109"
        ) {
            //TODO open location info
        }

        val episodeCardView = EpisodeCardView(
            id = 2,
            name = "Lawnmower Dog",
            episode = "S01E02",
            airDate = "December 9, 2013"
        ) {
            //TODO open episode info
        }

        val listOfAdapters =
            listOf(characterCardView, locationCardView, episodeCardView)

        setUpList(listOfAdapters)
    }

    companion object {
        private const val ARG_SELECTED_TYPE = "arg_selected_type"

        fun newInstance(context: Context, selectedType: Int) =
            Intent(context, CatalogueActivity::class.java).apply {
                putExtra(ARG_SELECTED_TYPE, selectedType)
            }
    }
}
