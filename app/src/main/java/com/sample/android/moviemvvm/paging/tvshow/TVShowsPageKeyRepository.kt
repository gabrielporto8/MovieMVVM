package com.sample.android.moviemvvm.paging.tvshow

import android.content.Context
import com.sample.android.moviemvvm.domain.TVShow
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyRepository
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
        private val api: TVShowApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<TVShow>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<TVShow> =
            TVShowsDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}