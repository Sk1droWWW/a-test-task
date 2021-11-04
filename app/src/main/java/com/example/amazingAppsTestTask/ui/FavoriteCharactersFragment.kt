package com.example.amazingAppsTestTask.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.viewmodels.FavoriteCharactersViewModel

class FavoriteCharactersFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteCharactersFragment()
    }

    private lateinit var viewModel: FavoriteCharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteCharactersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}