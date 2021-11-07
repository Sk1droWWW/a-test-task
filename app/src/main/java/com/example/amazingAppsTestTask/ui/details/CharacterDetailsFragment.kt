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
import com.example.amazingAppsTestTask.ui.search.CharactersSearchFragment
import com.example.amazingAppsTestTask.viewmodels.CharacterDetailsViewModel
import com.example.amazingAppsTestTask.viewmodels.CharacterDetailsViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private var bottomNav: BottomNavigationView? = null

    private val navigationArgs: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterDetailsViewModel by activityViewModels {
        CharacterDetailsViewModelFactory(
            (activity?.application as CharacterApplication).database
                .itemDao()
        )
    }

    companion object {
        fun newInstance() = CharactersSearchFragment()
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
        setRecycler()
    }

    private fun setCharacterInfo() {
//        val id = navigationArgs.characterId
//        if (id > 0) {
//            viewModel.retrieveCharacter(id).observe(this.viewLifecycleOwner) { selectedItem ->
////                bind(selectedItem)
//            }
//        }
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

    private fun setRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = FilmListAdapter()

        binding.recyclerView.adapter = adapter

        observeItems(adapter)
    }

    private fun observeItems(adapter: FilmListAdapter) {
//        viewModel.films.observe(viewLifecycleOwner) { items ->
//            adapter.submitList(items)
//        }
    }

}