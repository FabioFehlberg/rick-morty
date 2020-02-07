package com.fehlves.rickmorty.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.CatalogueActivity
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.main.model.CategoryView
import com.fehlves.rickmorty.main.model.TitleView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        val categoryCharacter = CategoryView(
            R.drawable.img_character,
            R.string.main_activity_card_characters,
            R.string.main_activity_character_description
        ) {
            startActivity(CatalogueActivity.newInstance(this@MainActivity))
        }

        val categoryLocation = CategoryView(
            R.drawable.img_location,
            R.string.main_activity_card_locations,
            R.string.main_activity_location_description
        ) {
            startActivity(CatalogueActivity.newInstance(this@MainActivity))
        }

        val categoryEpisode = CategoryView(
            R.drawable.img_episode,
            R.string.main_activity_card_episodes,
            R.string.main_activity_episode_description
        ) {
            startActivity(CatalogueActivity.newInstance(this@MainActivity))
        }

        val title = TitleView(
            R.drawable.img_rick_morty,
            R.string.main_activity_title_description
        )

        val listOfAdapters = listOf(title, categoryCharacter, categoryLocation, categoryEpisode)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = MainAdapter().apply { submitList(listOfAdapters) }
    }
}
