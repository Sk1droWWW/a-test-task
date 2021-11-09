package com.example.amazingAppsTestTask.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingAppsTestTask.databinding.LoadStateViewBinding

class SearchCharactersStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SearchCharactersStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private var binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.loadStateProgress.visibility =
                toVisibility(
                    loadState is LoadState.Loading
                            && !loadState.endOfPaginationReached
                )
            binding.loadStateErrorMessageTv.visibility =
                toVisibility(loadState !is LoadState.Loading)
            binding.loadStateRetryBtn.visibility =
                toVisibility(loadState !is LoadState.Loading)

            if (loadState is LoadState.Error) {
                binding.loadStateErrorMessageTv.text =
                    loadState.error.localizedMessage
            }

            binding.loadStateRetryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        private fun toVisibility(constraint: Boolean): Int =
            if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }
}