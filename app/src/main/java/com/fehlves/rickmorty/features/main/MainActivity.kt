package com.fehlves.rickmorty.features.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.features.catalogue.CatalogueActivity
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.databinding.ActivityMainBinding
import com.fehlves.rickmorty.features.main.model.CategoryView
import com.fehlves.rickmorty.features.main.model.TitleView

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = {
        ActivityMainBinding.inflate(it)
    }

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
            startActivity(CatalogueActivity.newInstance(this@MainActivity, CHARACTER_TYPE))
        }

        val categoryLocation = CategoryView(
            R.drawable.img_location,
            R.string.main_activity_card_locations,
            R.string.main_activity_location_description
        ) {
            startActivity(CatalogueActivity.newInstance(this@MainActivity, LOCATION_TYPE))
        }

        val categoryEpisode = CategoryView(
            R.drawable.img_episode,
            R.string.main_activity_card_episodes,
            R.string.main_activity_episode_description
        ) {
            startActivity(CatalogueActivity.newInstance(this@MainActivity, EPISODE_TYPE))
        }

        val title = TitleView(
            R.drawable.img_rick_morty,
            R.string.main_activity_title_description
        )

        val listOfAdapters = listOf(title, categoryCharacter, categoryLocation, categoryEpisode)

        binding.rvMain.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = MainAdapter().apply { submitList(listOfAdapters) }
        }
    }
}
