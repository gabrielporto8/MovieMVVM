package com.sample.android.moviemvvm.paging.tvshow

import android.content.Context
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class PageKeyedTVShowsDataSource(
        private val api: TVShowApi,
        private val sortType: SortType,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int) = when (sortType) {
        SortType.MOST_POPULAR -> api.popularItems(page)
        SortType.HIGHEST_RATED -> api.topRatedItems(page)
        SortType.UPCOMING -> api.latestItems(page)
    }
}