package com.fehlves.rickmorty.features.detail.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.databinding.ActivityCharacterDetailBinding
import com.fehlves.rickmorty.features.detail.DetailViewModel
import com.fehlves.rickmorty.extensions.extra
import com.fehlves.rickmorty.extensions.loadImageFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailActivity : BaseActivity<ActivityCharacterDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityCharacterDetailBinding = {
        ActivityCharacterDetailBinding.inflate(it)
    }

    private val viewModel: DetailViewModel by viewModel()

    private val characterEntity by extra<CharacterEntity>(ARG_CHARACTER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCharacterDetail.loadImageFromUrl(characterEntity.image)
        binding.ivCharacterDetail.contentDescription = getString(R.string.character_detail_image_description, characterEntity.name)

        //viewModel.loadDetails(characterId, CHARACTER_TYPE)
        //TODO make it possible to load this screen either from a url and a entity, cuz it could be accessed from other detail activities
        //TODO continue work on character layout screen

        setupObservables()
    }

    private fun setupObservables() {

    }

    companion object {
        private const val ARG_CHARACTER = "arg_character"

        fun newInstance(context: Context, characterId: CharacterEntity) =
            Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER, characterId)
            }
    }
}
