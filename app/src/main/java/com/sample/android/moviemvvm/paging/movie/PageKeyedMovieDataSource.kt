package com.sample.android.moviemvvm.paging.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class PageKeyedMovieDataSource(
        private val api: MovieApi,
        private val sortType: SortType,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int) = when (sortType) {
        SortType.MOST_POPULAR -> api.popularItems(page)
        SortType.HIGHEST_RATED -> api.topRatedItems(page)
        SortType.UPCOMING -> api.latestItems(page)
    }
}