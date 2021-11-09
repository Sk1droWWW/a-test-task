package com.example.amazingAppsTestTask.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.databinding.FilmItemBinding
import com.example.amazingAppsTestTask.domain.model.Film

class FilmListAdapter() :
    ListAdapter<Film, FilmListAdapter.ItemViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            FilmItemBinding.inflate(
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

        holder.bind(current)
    }

    class ItemViewHolder(private var binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.filmTitleTv.text = film.title
            binding.directorTv.text = film.director
            binding.releaseDateTv.text = film.date
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldFilm: Film, newFilm: Film): Boolean {
                return oldFilm === newFilm
            }

            override fun areContentsTheSame(oldFilm: Film, newFilm: Film): Boolean {
                return oldFilm.id == newFilm.id
            }
        }
    }
}