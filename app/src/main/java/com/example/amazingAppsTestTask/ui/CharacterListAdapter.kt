package com.example.amazingAppsTestTask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.databinding.CharacterItemBinding
import com.example.amazingAppsTestTask.model.database.Character

class CharacterListAdapter(private val onItemClicked: (Character) -> Unit) :
    ListAdapter<Character, CharacterListAdapter.ItemViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)

//        holder.itemView.setOnClickListener {
//            onItemClicked(current)
//        }
        holder.bind(current)
    }

    inner class ItemViewHolder(private var binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterNameTv.text = character.name
            binding.filmsAmountTv.text = character.films.toString()
            binding.birthYearTv.text = character.birthYear
            binding.favoriteBtn.setOnClickListener {
                onItemClicked(character)
//                binding.favoriteBtn.background = R.drawable.ic_baseline_favorite_24
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldCharacter: Character, newCharacter: Character): Boolean {
                return oldCharacter === newCharacter
            }

            override fun areContentsTheSame(oldCharacter: Character, newCharacter: Character): Boolean {
                return oldCharacter.id == newCharacter.id
            }
        }
    }
}