package com.fehlves.rickmorty.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.extensions.extra
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModel()

    private val characterId by extra<Int>(ARG_CHARACTER_ID)

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadDetails(characterId, CHARACTER_TYPE)

        setupObservables()
    }

    private fun setupObservables() {

    }

    companion object {
        private const val ARG_CHARACTER_ID = "arg_character_id"

        fun newInstance(context: Context, characterId: Int) =
            Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER_ID, characterId)
            }
    }
}
