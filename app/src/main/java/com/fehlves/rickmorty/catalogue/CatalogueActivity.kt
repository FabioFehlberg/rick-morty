package com.fehlves.rickmorty.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.CharacterCardView
import com.fehlves.rickmorty.catalogue.model.EpisodeCardView
import com.fehlves.rickmorty.catalogue.model.LocationCardView
import com.fehlves.rickmorty.catalogue.model.SearchView
import com.fehlves.rickmorty.common.BaseActivity
import kotlinx.android.synthetic.main.activity_catalogue.*
import kotlin.random.Random

class CatalogueActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        val searchView = SearchView(
            type = Random.nextInt(1,3)
        ) {
            //TODO call api
        }

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
            listOf(searchView, characterCardView, locationCardView, episodeCardView)

        rvCatalogue.layoutManager = LinearLayoutManager(this)
        rvCatalogue.adapter = CatalogueAdapter().apply { submitList(listOfAdapters) }
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, CatalogueActivity::class.java)
    }
}
