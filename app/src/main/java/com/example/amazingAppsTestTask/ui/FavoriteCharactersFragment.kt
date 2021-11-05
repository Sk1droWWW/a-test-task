package com.example.amazingAppsTestTask.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazingAppsTestTask.CharacterApplication
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.databinding.FragmentCharacterSearchBinding
import com.example.amazingAppsTestTask.databinding.FragmentFavoriteCharactersBinding
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModel
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModelFactory
import com.example.amazingAppsTestTask.viewmodels.FavoriteCharactersViewModel
import com.example.amazingAppsTestTask.viewmodels.FavoriteCharactersViewModelFactory

class FavoriteCharactersFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteCharactersBinding

    private val viewModel: FavoriteCharactersViewModel by activityViewModels {
        FavoriteCharactersViewModelFactory(
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
        binding = FragmentFavoriteCharactersBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycler()
    }

    private fun setRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = CharacterListAdapter {
            val action =
                FavoriteCharactersFragmentDirections
                    .actionFavoriteCharactersFragmentToCharacterDetailsFragment(
                        characterId = it.id
                    )
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        observeItems(adapter)
    }

    private fun observeItems(adapter: CharacterListAdapter) {
        viewModel.characters.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

}