package com.sample.android.moviemvvm.ui.item.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.Listing
import com.sample.android.moviemvvm.paging.movie.MoviePageKeyRepository
import com.sample.android.moviemvvm.ui.item.BaseItemViewModel
import com.sample.android.moviemvvm.util.SortType

class MovieViewModel(
        api: MovieApi,
        sortType: SortType,
        app: Application) : BaseItemViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        MoviePageKeyRepository(
                api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(NETWORK_IO)
    }
}