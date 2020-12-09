package com.sample.android.moviemvvm.paging.search.tvshow

import android.content.Context
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import java.util.concurrent.Executor

class PageKeyedSearchTVShowDataSource(
        private val api: TVShowApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int) = api.searchItems(page, query)
}