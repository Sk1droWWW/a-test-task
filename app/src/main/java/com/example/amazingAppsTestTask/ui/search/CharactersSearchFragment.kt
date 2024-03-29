package com.example.amazingAppsTestTask.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazingAppsTestTask.CharacterApplication
import com.example.amazingAppsTestTask.databinding.FragmentCharacterSearchBinding
import com.example.amazingAppsTestTask.domain.repository.StarWarsRepository
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModel
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersSearchFragment : Fragment() {

    private lateinit var binding: FragmentCharacterSearchBinding

    private val viewModel: CharacterSearchViewModel by activityViewModels {
        CharacterSearchViewModelFactory(
            StarWarsRepository(
                StarWarsApiService.getApiService(),
                (activity?.application as CharacterApplication).database
                    .itemDao()
            ),
        )
    }
    private var searchJob: Job? = null

    companion object {
        fun newInstance() = CharactersSearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterSearchBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {

        val adapter = SearchCharactersPagingDataAdapter(
            {
                val action =
                    CharactersSearchFragmentDirections
                        .actionCharactersSearchFragmentToCharacterDetailsFragment(
                            character = it
                        )
                this.findNavController().navigate(action)
            },
            { viewModel.characterItemClick(it) }
        )

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = SearchCharactersStateAdapter { adapter.retry() }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        setupSearchView(adapter)
        submitItems(adapter)
    }

    private fun setupSearchView(adapter: SearchCharactersPagingDataAdapter) {
        binding.searchView
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    search(query, adapter)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    search(newText, adapter)
                    return true
                }
            })
    }

    private fun search(query: String, adapter: SearchCharactersPagingDataAdapter) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchCharacters(query)
        }

        submitItems(adapter)
    }

    private fun submitItems(adapter: SearchCharactersPagingDataAdapter) {
        lifecycleScope.launch {
            viewModel.listData?.collect {
                adapter.submitData(it)
            }
        }
    }

}