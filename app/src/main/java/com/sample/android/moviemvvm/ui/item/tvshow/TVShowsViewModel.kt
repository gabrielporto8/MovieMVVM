package com.sample.android.moviemvvm.ui.item.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.Listing
import com.sample.android.moviemvvm.paging.tvshow.TVShowsPageKeyRepository
import com.sample.android.moviemvvm.ui.item.BaseItemViewModel
import com.sample.android.moviemvvm.util.SortType

class TVShowsViewModel(
        api: TVShowApi,
        sortType: SortType,
        app: Application) : BaseItemViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        TVShowsPageKeyRepository(
                api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(NETWORK_IO)
    }
}