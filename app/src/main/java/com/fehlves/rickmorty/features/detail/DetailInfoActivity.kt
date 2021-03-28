package com.fehlves.rickmorty.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fehlves.rickmorty.R
import com.fehlves.rickmorty.common.BaseActivity
import com.fehlves.rickmorty.common.BaseEntity
import com.fehlves.rickmorty.data.CharacterEntity
import com.fehlves.rickmorty.data.EpisodeEntity
import com.fehlves.rickmorty.data.LocationEntity
import com.fehlves.rickmorty.databinding.ActivityCharacterDetailBinding
import com.fehlves.rickmorty.extensions.extra
import com.fehlves.rickmorty.extensions.formatDate
import com.fehlves.rickmorty.extensions.loadImageFromUrl
import com.fehlves.rickmorty.extensions.observeNullable
import com.fehlves.rickmorty.features.detail.model.BaseDetailInfoView
import com.fehlves.rickmorty.features.detail.model.DetailInfoCardView
import com.fehlves.rickmorty.features.detail.model.DetailInfoView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailInfoActivity : BaseActivity<ActivityCharacterDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityCharacterDetailBinding = {
        ActivityCharacterDetailBinding.inflate(it)
    }

    private val viewModel: DetailViewModel by viewModel()

    private val itemsList: ArrayList<BaseDetailInfoView> = arrayListOf()

    private val detailEntity by extra<BaseEntity>(ARG_DETAIL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            vm.onCharacterListResult().observeNullable(this) {
                when (detailEntity) {
                    is LocationEntity -> setResidentsList(it)
                    is EpisodeEntity -> setCharactersAppearanceList(it)
                }
            }
        }
    }

    // region Character detail

    private fun setupCharacterView(character: CharacterEntity) {
        character.also {
            with(binding) {
                ivCharacterDetail.loadImageFromUrl(it.image)
                ivCharacterDetail.contentDescription = getString(R.string.character_detail_image_description, it.name)

                tvName.text = it.name

                val characterList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.character_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.character_detail_gender, it.gender)))
                    add(DetailInfoView(getString(R.string.character_detail_location, it.location.name)))
                    add(DetailInfoView(getString(R.string.character_detail_origin, it.origin.name)))
                    add(DetailInfoView(getString(R.string.character_detail_species, it.species)))
                    add(DetailInfoView(getString(R.string.character_detail_status, it.status)))
                    it.type.takeIf { tp -> tp.isNotBlank() }
                        ?.let { type -> add(DetailInfoView(getString(R.string.character_detail_type, type))) }
                    add(DetailInfoView(getString(R.string.character_detail_episode_list), isLoading = true))
                }

                val listOfEpisodes = it.episode.map { it.split("/").last() }
                when (listOfEpisodes.count()) {
                    0 -> run { } //TODO("add empty state")
                    1 -> viewModel.loadEpisode(listOfEpisodes.first().toInt())
                    else -> viewModel.loadEpisodesList(listOfEpisodes.joinToString())
                }

                itemsList += characterList
                rvDetailInfo.adapter?.notifyDataSetChanged()
                nsContent.smoothScrollTo(0, 0)
            }
        }
    }

    private fun setCharacterEpisodeList(episodes: List<EpisodeEntity>?) {
        (itemsList.last() as DetailInfoView).isLoading = false
        episodes?.let {
            if (it.isEmpty()) {
                // TODO("add empty state")
            } else itemsList += it.map { ep -> ep.toDetailInfoCardView() }
        } // ?: TODO("add error state")
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

                val characterList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.location_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.location_detail_dimension, it.dimension)))
                    add(DetailInfoView(getString(R.string.location_detail_type, it.type)))
                    add(DetailInfoView(getString(R.string.location_detail_resident_list), isLoading = true))
                }

                val listOfResidents = it.residents.map { it.split("/").last() }
                when (listOfResidents.count()) {
                    0 -> run { } //TODO("add empty state")
                    1 -> viewModel.loadCharacter(listOfResidents.first().toInt())
                    else -> viewModel.loadCharacterList(listOfResidents.joinToString())
                }

                itemsList += characterList
                rvDetailInfo.adapter?.notifyDataSetChanged()
                nsContent.smoothScrollTo(0, 0) // todo know why it is going to top
            }
        }
    }

    private fun setResidentsList(characters: List<CharacterEntity>?) {
        (itemsList.last() as DetailInfoView).isLoading = false
        characters?.let {
            if (it.isEmpty()) {
                // TODO("add empty state")
            } else itemsList += it.map { ep -> ep.toDetailInfoCardView() }
        } // ?: TODO("add error state")
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    // endregion

    // region Episode detail

    private fun setupEpisodeView(episode: EpisodeEntity) {
        episode.also {
            with(binding) {
                ivCharacterDetail.visibility = View.GONE

                tvName.text = it.name

                val characterList = mutableListOf<BaseDetailInfoView>().apply {
                    add(DetailInfoView(getString(R.string.episode_detail_creation_date, it.created.formatDate())))
                    add(DetailInfoView(getString(R.string.episode_detail_air_date, it.airDate)))
                    add(DetailInfoView(getString(R.string.episode_detail_episode, it.episode)))
                    add(DetailInfoView(getString(R.string.episode_detail_character_list), isLoading = true))
                }

                val listOfCharacters = it.characters.map { it.split("/").last() }
                when (listOfCharacters.count()) {
                    0 -> run { } //TODO("add empty state")
                    1 -> viewModel.loadCharacter(listOfCharacters.first().toInt())
                    else -> viewModel.loadCharacterList(listOfCharacters.joinToString())
                }

                itemsList += characterList
                rvDetailInfo.adapter?.notifyDataSetChanged()
                nsContent.smoothScrollTo(0, 0)
            }
        }
    }

    private fun setCharactersAppearanceList(characters: List<CharacterEntity>?) {
        (itemsList.last() as DetailInfoView).isLoading = false
        characters?.let {
            if (it.isEmpty()) {
                // TODO("add empty state")
            } else itemsList += it.map { ep -> ep.toDetailInfoCardView() }
        } // ?: TODO("add error state")
        binding.rvDetailInfo.adapter?.notifyDataSetChanged()
    }

    // endregion

    private fun CharacterEntity.toDetailInfoCardView() = DetailInfoCardView(label = name, iconUrl = image) {
        startActivity(newInstance(this@DetailInfoActivity, this))
    }

    companion object {
        private const val ARG_DETAIL = "arg_detail"

        fun newInstance(context: Context, entity: BaseEntity) = Intent(context, DetailInfoActivity::class.java).apply {
            putExtra(ARG_DETAIL, entity)
        }
    }
}
