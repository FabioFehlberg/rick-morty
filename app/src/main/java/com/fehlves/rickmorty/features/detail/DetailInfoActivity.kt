package com.fehlves.rickmorty.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.common.BaseEntity
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity
import com.fehlves.rickmorty.databinding.ActivityDetailInfoBinding
import com.fehlves.rickmorty.extensions.*
import com.fehlves.rickmorty.features.detail.model.BaseDetailInfoView
import com.fehlves.rickmorty.features.detail.model.DetailInfoCardView
import com.fehlves.rickmorty.features.detail.model.DetailInfoView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailInfoActivity : BaseActivity() {

    override val binding by viewBinding { ActivityDetailInfoBinding.inflate(layoutInflater) }

    private val viewModel: DetailViewModel by viewModel()

    private val itemsList: ArrayList<BaseDetailInfoView> = arrayListOf()

    private val detailEntity by extra<BaseEntity>(ARG_DETAIL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAppBar(binding.appBarDetailInfo.toolbarWidget)

        when (detailEntity) {
            is CharacterEntity -> setupCharacterView(detailEntity as CharacterEntity)
            is LocationEntity -> setupLocationView(detailEntity as LocationEntity)
            is EpisodeEntity -> setupEpisodeView(detailEntity as EpisodeEntity)
        }

        setupObservables()
        setupList()
    }

    private fun setupList() {
        binding.rvDetailInfo.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = DetailAdapter().apply { submitList(itemsList) }
        }
    }

    private fun setupObservables() {
        viewModel.also { vm ->
            vm.onEpisodeListResult().observeNullable(this, ::setCharacterEpisodeList)
            vm.onCharacterListResult().observeNullable(this, ::setCharacterList)
        }
    }

    // region Character detail

    private fun setupCharacterView(character: CharacterEntity) {
        character.also {
            with(binding) {
                ivCharacterDetail.loadImageFromUrl(it.image)
                ivCharacterDetail.contentDescription = getString(R.string.character_detail_image_description, it.name)

                tvName.text = it.name

                val listOfEpisodes = it.episode.map { it.split("/").last() }

                val characterList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.character_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.character_detail_gender, it.gender)))
                    add(DetailInfoView(getString(R.string.character_detail_location, it.location.name)))
                    add(DetailInfoView(getString(R.string.character_detail_origin, it.origin.name)))
                    add(DetailInfoView(getString(R.string.character_detail_species, it.species)))
                    add(DetailInfoView(getString(R.string.character_detail_status, it.status)))
                    it.type.takeIf { tp -> tp.isNotBlank() }
                        ?.let { type -> add(DetailInfoView(getString(R.string.character_detail_type, type))) }
                    add(
                        DetailInfoView(getString(R.string.character_detail_episode_list), isLoading = true) { loadEpisodes(listOfEpisodes) }
                    )
                }

                loadEpisodes(listOfEpisodes)

                itemsList += characterList
                rvDetailInfo.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun loadEpisodes(listOfEpisodes: List<String>) {
        when (listOfEpisodes.count()) {
            0 -> setEmptyEpisodeList()
            1 -> viewModel.loadEpisode(listOfEpisodes.first().toInt())
            else -> viewModel.loadEpisodesList(listOfEpisodes.joinToString())
        }
    }

    private fun setCharacterEpisodeList(episodes: List<EpisodeEntity>?) {
        (itemsList.last() as DetailInfoView).isLoading = false
        episodes?.let {
            if (it.isEmpty()) setEmptyEpisodeList()
            else itemsList += it.map { ep -> ep.toDetailInfoCardView() }
        } ?: run { (itemsList.last() as DetailInfoView).showRefresh = true }
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    private fun setEmptyEpisodeList() {
        (itemsList.last() as DetailInfoView).isLoading = false
        itemsList += DetailInfoCardView(getString(R.string.character_detail_episode_empty_list))
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    private fun EpisodeEntity.toDetailInfoCardView() = DetailInfoCardView(label = "$episode - $name") {
        startActivity(newInstance(this@DetailInfoActivity, this))
    }

    // endregion

    // region Location detail

    private fun setupLocationView(location: LocationEntity) {
        location.also {
            with(binding) {
                ivCharacterDetail.visibility = View.GONE

                tvName.text = it.name

                val listOfResidents = it.residents.map { it.split("/").last() }

                val locationList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.location_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.location_detail_dimension, it.dimension)))
                    add(DetailInfoView(getString(R.string.location_detail_type, it.type)))
                    add(DetailInfoView(getString(R.string.location_detail_resident_list), isLoading = true) {
                        loadCharacterList(listOfResidents)
                    })
                }

                loadCharacterList(listOfResidents)

                itemsList += locationList
                rvDetailInfo.adapter?.notifyDataSetChanged()
            }
        }
    }

    // endregion

    // region Episode detail

    private fun setupEpisodeView(episode: EpisodeEntity) {
        episode.also {
            with(binding) {
                ivCharacterDetail.visibility = View.GONE

                tvName.text = it.name

                val listOfCharacters = it.characters.map { it.split("/").last() }

                val episodeList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.episode_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.episode_detail_air_date, it.airDate)))
                    add(DetailInfoView(getString(R.string.episode_detail_episode, it.episode)))
                    add(DetailInfoView(getString(R.string.episode_detail_character_list), isLoading = true) {
                        loadCharacterList(listOfCharacters)
                    })
                }

                loadCharacterList(listOfCharacters)

                itemsList += episodeList
                rvDetailInfo.adapter?.notifyDataSetChanged()
            }
        }
    }

    // endregion

    private fun CharacterEntity.toDetailInfoCardView() = DetailInfoCardView(label = name, iconUrl = image) {
        startActivity(newInstance(this@DetailInfoActivity, this))
    }

    private fun loadCharacterList(listOfCharacters: List<String>) {
        when (listOfCharacters.count()) {
            0 -> setEmptyCharacterList()
            1 -> viewModel.loadCharacter(listOfCharacters.first().toInt())
            else -> viewModel.loadCharacterList(listOfCharacters.joinToString())
        }
    }

    private fun setCharacterList(characters: List<CharacterEntity>?) {
        (itemsList.last() as DetailInfoView).isLoading = false
        characters?.let {
            if (it.isEmpty()) setEmptyCharacterList()
            else itemsList += it.map { ep -> ep.toDetailInfoCardView() }
        } ?: run { (itemsList.last() as DetailInfoView).showRefresh = true }
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    private fun setEmptyCharacterList() {
        (itemsList.last() as DetailInfoView).isLoading = false
        itemsList += DetailInfoCardView(getString(R.string.episode_detail_character_empty_list))
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    companion object {
        private const val ARG_DETAIL = "arg_detail"

        fun newInstance(context: Context, entity: BaseEntity) = Intent(context, DetailInfoActivity::class.java).apply {
            putExtra(ARG_DETAIL, entity)
        }
    }
}
