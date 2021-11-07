package com.example.amazingAppsTestTask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.databinding.CharacterItemBinding
import com.example.amazingAppsTestTask.network.dto.NetworkCharacter

class FavoriteCharactersListAdapter(private val onItemClicked: (NetworkCharacter) -> Unit) :
    ListAdapter<NetworkCharacter, FavoriteCharactersListAdapter.ItemViewHolder>(DiffCallback) {
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
        fun bind(networkCharacter: NetworkCharacter) {
            binding.characterNameTv.text = networkCharacter.name
            binding.heightTv.text = networkCharacter.height
            binding.massTv.text = networkCharacter.mass
            binding.favoriteBtn.setOnClickListener {
                onItemClicked(networkCharacter)
//                binding.favoriteBtn.background = R.drawable.ic_baseline_favorite_24
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NetworkCharacter>() {
            override fun areItemsTheSame(oldNetworkCharacter: NetworkCharacter, newNetworkCharacter: NetworkCharacter): Boolean {
                return oldNetworkCharacter === newNetworkCharacter
            }

            override fun areContentsTheSame(oldNetworkCharacter: NetworkCharacter, newNetworkCharacter: NetworkCharacter): Boolean {
                return oldNetworkCharacter == newNetworkCharacter
            }
        }
    }
}