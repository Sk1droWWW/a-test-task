package com.example.amazingAppsTestTask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.R
import com.example.amazingAppsTestTask.databinding.CharacterItemBinding
import com.example.amazingAppsTestTask.domain.model.Character

class SearchCharactersPagingDataAdapter(
    private val onItemClicked: (Character) -> Unit,
    private val onFavoriteClicked: (Character) -> Unit
) :
    PagingDataAdapter<Character, SearchCharactersPagingDataAdapter.ItemViewHolder>(DiffCallback) {
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

        holder.itemView.setOnClickListener {
            current?.let { onItemClicked(it) }
        }
        current?.let {
            holder.bind(it)
        }
    }

    inner class ItemViewHolder(private var binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterNameTv.text = character.name
            binding.heightTv.text = character.height
            binding.massTv.text = character.mass
            setFavoriteBackground(character)

            binding.favoriteBtn.setOnClickListener {
                onFavoriteClicked(character)
                setFavoriteBackground(character)
            }
        }

        private fun setFavoriteBackground(character: Character) {
            if (character.favorite) {
                binding.favoriteBtn
                    .setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.favoriteBtn
                    .setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldCharacter: Character,
                newCharacter: Character
            ): Boolean {
                return oldCharacter.id == newCharacter.id
            }

            override fun areContentsTheSame(
                oldCharacter: Character,
                newCharacter: Character
            ): Boolean {
                return oldCharacter.id == newCharacter.id
            }
        }
    }
}