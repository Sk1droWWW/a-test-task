package com.example.amazingAppsTestTask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.database.dto.DBFilm
import com.example.amazingAppsTestTask.databinding.FilmItemBinding

class FilmListAdapter() :
    ListAdapter<DBFilm, FilmListAdapter.ItemViewHolder>(DiffCallback) {
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

        fun bind(DBFilm: DBFilm) {
            binding.filmTitleTv.text = DBFilm.title
            binding.directorTv.text = DBFilm.director
            binding.releaseDateTv.text = DBFilm.date
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DBFilm>() {
            override fun areItemsTheSame(oldDBFilm: DBFilm, newDBFilm: DBFilm): Boolean {
                return oldDBFilm === newDBFilm
            }

            override fun areContentsTheSame(oldDBFilm: DBFilm, newDBFilm: DBFilm): Boolean {
                return oldDBFilm.id == newDBFilm.id
            }
        }
    }
}