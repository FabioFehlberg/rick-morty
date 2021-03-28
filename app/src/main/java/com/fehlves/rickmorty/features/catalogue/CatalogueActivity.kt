package com.fehlves.rickmorty.features.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.databinding.ActivityCatalogueBinding
import com.fehlves.rickmorty.extensions.extra
import com.fehlves.rickmorty.extensions.observeNotNull
import com.fehlves.rickmorty.features.catalogue.model.CatalogueView
import com.fehlves.rickmorty.features.catalogue.model.LoadingCardView
import com.fehlves.rickmorty.features.catalogue.model.SearchView
import com.fehlves.rickmorty.features.detail.DetailInfoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogueActivity : BaseActivity<ActivityCatalogueBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityCatalogueBinding = {
        ActivityCatalogueBinding.inflate(it)
    }

    private val selectedType by extra<Int>(ARG_SELECTED_TYPE)

    private val viewModel: CatalogueViewModel by viewModel()

    private val itemsList: ArrayList<CatalogueView> = arrayListOf()

    private val linearLayoutManager by lazy { LinearLayoutManager(this) }

    private var searchInput = ""

    private val endlessScrollListener by lazy {
        object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                binding.rvCatalogue.removeOnScrollListener(this)
                loadItems(page)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAppBar(binding.appBarCatalogue.toolbarWidget)

        viewModel.setSelectedType(selectedType)

        setupSearchView()
        setupList()

        setupObservables()
    }

    private fun setupSearchView() {
        val searchView = SearchView(
            type = selectedType
        ) {
            searchInput = it
            resetList()
        }

        itemsList += searchView
    }

    private fun setupList() {
        binding.rvCatalogue.apply {
            layoutManager = linearLayoutManager
            adapter = CatalogueAdapter().apply { submitList(itemsList) }
            addOnScrollListener(endlessScrollListener)
        }
    }

    private fun resetList() {
        binding.rvCatalogue.removeOnScrollListener(endlessScrollListener)
        endlessScrollListener.resetState()
        val searchView = itemsList.first()
        viewModel.resetListOfItems()
        itemsList.clear()
        itemsList += searchView
        binding.rvCatalogue.addOnScrollListener(endlessScrollListener)
    }

    private fun setupObservables() {
        viewModel.onCharacterResult().observeNotNull(this) { items ->
            items.forEach { item ->
                item.onClick = {
                    viewModel.getItemEntityById(item.id)?.let { startActivity(DetailInfoActivity.newInstance(this, it)) }
                }
            }
            setupNewItems(items)
        }

        viewModel.onLocationResult().observeNotNull(this) { items ->
            items.forEach { item ->
                item.onClick = {
                    viewModel.getItemEntityById(item.id)?.let { startActivity(DetailInfoActivity.newInstance(this, it)) }
                }
            }
            setupNewItems(items)
        }

        viewModel.onEpisodeResult().observeNotNull(this) { items ->
            items.forEach { item ->
                item.onClick = {
                    viewModel.getItemEntityById(item.id)?.let { startActivity(DetailInfoActivity.newInstance(this, it)) }
                }
            }
            setupNewItems(items)
        }

        viewModel.onShowLoading().observeNotNull(this) { isToShow ->
            if (isToShow) showLoadingNewItems()
            else hideLoadingNewItems()
        }
    }

    private fun setupNewItems(items: List<CatalogueView>) {
        itemsList += items
        binding.rvCatalogue.apply {
            adapter?.notifyDataSetChanged()
            addOnScrollListener(endlessScrollListener)
        }
    }

    private fun loadItems(page: Int) {
        viewModel.loadMoreItems(page, searchInput)
    }

    private fun showLoadingNewItems() {
        itemsList += LoadingCardView()
        binding.rvCatalogue.adapter?.notifyDataSetChanged()
    }

    private fun hideLoadingNewItems() {
        itemsList.find { it.id == Int.MAX_VALUE }?.let {
            itemsList.remove(it)
            binding.rvCatalogue.adapter?.notifyDataSetChanged()
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
