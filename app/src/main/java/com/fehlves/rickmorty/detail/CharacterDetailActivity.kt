package com.fehlves.rickmorty.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity

class CharacterDetailActivity : BaseActivity() {

    //private val viewModel: CatalogueViewModel by viewModel()

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservables()
    }

    private fun setupObservables() {

    }

    companion object {
        private const val ARG_SELECTED_TYPE = "arg_selected_type"

        fun newInstance(context: Context, selectedType: Int) =
            Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_SELECTED_TYPE, selectedType)
            }
    }
}
