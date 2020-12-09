package com.sample.android.moviemvvm.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.Listing
import com.sample.android.moviemvvm.paging.search.movie.SearchMoviePageKeyRepository
import com.sample.android.moviemvvm.ui.TmdbViewModel

class SearchMovieViewModel(api: MovieApi, app: Application) : TmdbViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        SearchMoviePageKeyRepository(api, it, app.applicationContext).getItems(NETWORK_IO)
    }
}