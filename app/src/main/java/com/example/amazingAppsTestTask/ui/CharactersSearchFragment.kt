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
import com.example.amazingAppsTestTask.model.database.Character
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModel
import com.example.amazingAppsTestTask.viewmodels.CharacterSearchViewModelFactory

class CharactersSearchFragment : Fragment() {

    private lateinit var binding: FragmentCharacterSearchBinding

    private val viewModel: CharacterSearchViewModel by activityViewModels {
        CharacterSearchViewModelFactory(
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
        binding = FragmentCharacterSearchBinding.inflate(
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
//            viewModel.saveCharacter(it)
            val action =
                CharactersSearchFragmentDirections
                    .actionCharactersSearchFragmentToCharacterDetailsFragment(
                        characterId = it.id
                    )
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
        adapter.submitList(demoList())

//        observeItems(adapter)
    }

    private fun demoList() : List<Character> {
        return listOf(Character(
            name = "Luke Sky",
            height = "178",
            mass = "74",
            hairColor = "Blonde",
            skinColor = "White",
            eyeColor = "Blue",
            birthYear = "11BBYY",
            gender = "Male",
            films = 9))
    }

    private fun observeItems(adapter: CharacterListAdapter) {

    }

}