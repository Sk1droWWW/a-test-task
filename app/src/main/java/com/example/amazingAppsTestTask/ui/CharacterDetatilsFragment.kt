package com.example.amazingAppsTestTask.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.viewmodels.CharacterDetatilsViewModel

class CharacterDetatilsFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterDetatilsFragment()
    }

    private lateinit var viewModel: CharacterDetatilsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_detatils_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetatilsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}