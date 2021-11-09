package com.example.amazingAppsTestTask.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazingAppsTestTask.CharacterApplication
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.databinding.FragmentCharacterDetailsBinding
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.domain.repository.StarWarsRepository
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.viewmodels.CharacterDetailsViewModel
import com.example.amazingAppsTestTask.viewmodels.CharacterDetailsViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private var bottomNav: BottomNavigationView? = null

    private val navigationArgs: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterDetailsViewModel by activityViewModels {
        CharacterDetailsViewModelFactory(
            StarWarsRepository(
                StarWarsApiService.getApiService(),
                (activity?.application as CharacterApplication).database
                    .itemDao()
            ),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavVisibility(View.GONE)

        setCharacterInfo()
    }

    private fun setCharacterInfo() {
        val character = navigationArgs.character

        viewModel.setButtonsState(character.favorite)
        observeButtonsState()
        bind(character)
    }

    private fun bind(item: Character) {
        binding.apply {
            characterNameTv.setText(item.name, TextView.BufferType.SPANNABLE)
            characterHeightTv.setText(item.height, TextView.BufferType.SPANNABLE)
            characterMassTv.setText(item.mass, TextView.BufferType.SPANNABLE)
            characterBirthYearTv.setText(item.birthYear, TextView.BufferType.SPANNABLE)
            characterGenderTv.setText(item.gender, TextView.BufferType.SPANNABLE)
            characterHairColorTv.setText(item.hairColor, TextView.BufferType.SPANNABLE)
            characterSkinColorTv.setText(item.skinColor, TextView.BufferType.SPANNABLE)

            removeBtn.setOnClickListener {
                item.favorite = false
                viewModel.deleteCharacter(item)
            }
            addBtn.setOnClickListener {
                item.favorite = true
                viewModel.saveCharacter(item)
            }

            setRecycler(item.films)
        }
    }

    private fun observeButtonsState() {
        viewModel.btnState.observe(viewLifecycleOwner) { state ->
            binding.addBtn.isEnabled = !state
            binding.removeBtn.isEnabled = state
        }
    }

    private fun setBottomNavVisibility(state: Int) {
        bottomNav = activity?.findViewById(R.id.bottom_nav)
        bottomNav?.visibility = state
    }

    override fun onDestroyView() {
        super.onDestroyView()

        setBottomNavVisibility(View.VISIBLE)
    }

    private fun setRecycler(items: List<Film?>) {
        val adapter = FilmListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerView.adapter = adapter

        adapter.submitList(items)
    }

    private fun observeItems(adapter: FilmListAdapter) {
//        viewModel.films.observe(viewLifecycleOwner) { items ->
//            adapter.submitList(items)
//        }
    }

}