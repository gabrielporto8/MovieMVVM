package com.sample.android.moviemvvm.paging.tvshow

import android.content.Context
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        private val api: TVShowApi,
        private val sortType: SortType,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<TVShow>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow> =
            PageKeyedTVShowsDataSource(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}