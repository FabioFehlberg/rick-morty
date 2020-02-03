package com.fehlves.rickmorty.main

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        val categoryCharacter = CardAdapterItem(
            getDrawable(R.drawable.img_character),
            getString(R.string.main_activity_card_characters),
            getString(R.string.main_activity_character_description)
        ) {
            Log.d("TAGATA", "categoryCharacter")
        }

        val categoryLocation = CardAdapterItem(
            getDrawable(R.drawable.img_character),
            getString(R.string.main_activity_card_locations),
            getString(R.string.main_activity_location_description)
        ) {
            Log.d("TAGATA", "categoryLocation")
        }

        val categoryEpisode = CardAdapterItem(
            getDrawable(R.drawable.img_character),
            getString(R.string.main_activity_card_episodes),
            getString(R.string.main_activity_episode_description)
        ) {
            Log.d("TAGATA", "categoryEpisode")
        }

        val title = TitleAdapterItem(
            getDrawable(R.drawable.img_rick_morty),
            getString(R.string.main_activity_title_description)
        ) {
            Log.d("TAGATA", "image")
        }

        val listOfAdapters = listOf(title, categoryCharacter, categoryLocation, categoryEpisode)

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = MainAdapter(listOfAdapters)
    }
}
