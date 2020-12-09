package com.sample.android.moviemvvm.ui.item.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.ui.item.BaseItemFragment
import javax.inject.Inject

abstract class MovieFragment : BaseItemFragment<Movie>() {

    @Inject
    lateinit var api: MovieApi

    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(api, sortType, requireNotNull(activity).application) as T
            }
        })[MovieViewModel::class.java]
    }
}