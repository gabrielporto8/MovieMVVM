package com.sample.android.moviemvvm.ui

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.databinding.TmdbItemBinding
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.util.layoutInflater

class TmdbItemViewHolder(internal val binding: TmdbItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <T : TmdbItem> create(parent: ViewGroup, tmdbClickCallback: TmdbClickCallback<T>): TmdbItemViewHolder {
            val binding: TmdbItemBinding = DataBindingUtil
                    .inflate(parent.context.layoutInflater,
                            R.layout.tmdb_item,
                            parent, false)
            with(binding) {
                poster = itemPoster
                name = itemName
                callback = tmdbClickCallback
            }
            return TmdbItemViewHolder(binding)
        }
    }
}