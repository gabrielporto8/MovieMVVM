package com.sample.android.moviemvvm.paging.search.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.ItemWrapper
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import retrofit2.Call
import java.util.concurrent.Executor

class PageKeyedSearchMovieDataSource(
        private val api: MovieApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int): Call<ItemWrapper<Movie>> =
            api.searchItems(page, query)
}