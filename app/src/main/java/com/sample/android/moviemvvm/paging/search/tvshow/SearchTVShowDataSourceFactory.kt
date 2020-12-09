package com.sample.android.moviemvvm.paging.search.tvshow

import android.content.Context
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        private val api: TVShowApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<TVShow>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow> =
            PageKeyedSearchTVShowDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}