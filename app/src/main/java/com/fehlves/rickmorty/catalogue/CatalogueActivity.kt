package com.fehlves.rickmorty.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.catalogue.model.*
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.common.Constants.Companion.CHARACTER_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.EPISODE_TYPE
import com.fehlves.rickmorty.common.Constants.Companion.LOCATION_TYPE
import com.fehlves.rickmorty.extensions.extra
import com.fehlves.rickmorty.extensions.observeNotNull
import kotlinx.android.synthetic.main.activity_catalogue.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

class CatalogueActivity : BaseActivity() {

    private val selectedType by extra<Int>(ARG_SELECTED_TYPE)

    private val viewModel: CatalogueViewModel by viewModel()

    private val itemsList: ArrayList<CatalogueView> = arrayListOf()

    override fun getContentLayoutId() = R.layout.activity_catalogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearchView()
        setupList()

        viewModel.onCharacterResult().observeNotNull(this) { items ->
            items.forEach {
                it.onClick = {
                    val myUrlToPass = it.url
                    //startActivity() TODO start activity passing url
                    Log.d("MY_TAG", it.url)
                }
            }
            setupNewItems(items)
        }

        loadItems(0)
    }

    private fun setupList() {
        val linearLayoutManager = LinearLayoutManager(this)
        rvCatalogue.layoutManager = linearLayoutManager
        rvCatalogue.adapter = CatalogueAdapter().apply { submitList(itemsList) }

        rvCatalogue.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadItems(page)
            }
        })
    }

    private fun setupSearchView() {
        val searchView = SearchView(
            type = selectedType
        ) {
            //TODO call api
        }

        itemsList += searchView
    }

    private fun setupNewItems(items: List<CatalogueView>) {
        itemsList += items
        rvCatalogue.adapter?.notifyDataSetChanged()
    }

    private fun loadItems(page: Int) {
        when (selectedType) {
            CHARACTER_TYPE -> viewModel.loadCharacters(page)
            LOCATION_TYPE -> viewModel.loadCharacters(page)
            EPISODE_TYPE -> viewModel.loadCharacters(page)
            else -> throw IllegalStateException("Incorrect type")
        }
    }

    companion object {
        private const val ARG_SELECTED_TYPE = "arg_selected_type"

        fun newInstance(context: Context, selectedType: Int) =
            Intent(context, CatalogueActivity::class.java).apply {
                putExtra(ARG_SELECTED_TYPE, selectedType)
            }
    }
}
