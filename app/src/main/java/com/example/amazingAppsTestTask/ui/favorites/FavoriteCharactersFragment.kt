package com.example.amazingAppsTestTask.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazingAppsTestTask.CharacterApplication
import com.example.amazingAppsTestTask.databinding.FragmentFavoriteCharactersBinding
import com.example.amazingAppsTestTask.domain.repository.StarWarsRepository
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.viewmodels.FavoriteCharactersViewModel
import com.example.amazingAppsTestTask.viewmodels.FavoriteCharactersViewModelFactory

class FavoriteCharactersFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteCharactersBinding

    private val viewModel: FavoriteCharactersViewModel by activityViewModels {
        FavoriteCharactersViewModelFactory(
            StarWarsRepository(
                StarWarsApiService.getApiService(),
                (activity?.application as CharacterApplication).database
                    .itemDao()),
        )
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
        val adapter = FavoriteCharactersListAdapter(
            {
                val action =
                    FavoriteCharactersFragmentDirections
                        .actionFavoriteCharactersFragmentToCharacterDetailsFragment(
                            character = it
                        )
               this.findNavController().navigate(action)
            },
            { viewModel.deleteCharacter(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        observeItems(adapter)
    }

    private fun observeItems(adapter: FavoriteCharactersListAdapter) {
        viewModel.characters.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
    }

}