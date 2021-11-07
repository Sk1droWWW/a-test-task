package com.example.amazingAppsTestTask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.databinding.CharacterItemBinding
import com.example.amazingAppsTestTask.model.network.model.Character

class CharacterListAdapter(private val onItemClicked: (Character) -> Unit) :
    PagingDataAdapter<Character, CharacterListAdapter.ItemViewHolder>(DiffCallback) {
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
        current?.let { holder.bind(it) }
    }

    inner class ItemViewHolder(private var binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterNameTv.text = character.name
            binding.heightTv.text = character.height
            binding.massTv.text = character.mass
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
                return oldCharacter == newCharacter
            }
        }
    }
}