package com.sample.android.moviemvvm.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.Listing
import com.sample.android.moviemvvm.paging.search.tvshow.SearchTVShowPageKeyRepository
import com.sample.android.moviemvvm.ui.TmdbViewModel

class SearchTVShowViewModel(api: TVShowApi, app: Application) : TmdbViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        SearchTVShowPageKeyRepository(api, it, app.applicationContext).getItems(NETWORK_IO)
    }
}