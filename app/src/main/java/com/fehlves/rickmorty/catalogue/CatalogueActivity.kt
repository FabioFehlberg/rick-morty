package com.fehlves.rickmorty.catalogue

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import kotlinx.android.synthetic.main.activity_catalogue.*

class CatalogueActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        /*val categoryCharacter = CategoryView(
            R.drawable.img_character,
            R.string.main_activity_card_characters,
            R.string.main_activity_character_description
        ) {
            Log.d("TAGATA", "categoryCharacter")
        }

        val categoryLocation = CategoryView(
            R.drawable.img_location,
            R.string.main_activity_card_locations,
            R.string.main_activity_location_description
        ) {
            Log.d("TAGATA", "categoryLocation")
        }

        val categoryEpisode = CategoryView(
            R.drawable.img_episode,
            R.string.main_activity_card_episodes,
            R.string.main_activity_episode_description
        ) {
            Log.d("TAGATA", "categoryEpisode")
        }

        val title = TitleView(
            R.drawable.img_rick_morty,
            R.string.main_activity_title_description
        )

        val listOfAdapters = listOf(title, categoryCharacter, categoryLocation, categoryEpisode)*/

        rvCatalogue.layoutManager = LinearLayoutManager(this)
        //rvCatalogue.adapter = MainAdapter().apply { submitList(listOfAdapters) }
    }
}
