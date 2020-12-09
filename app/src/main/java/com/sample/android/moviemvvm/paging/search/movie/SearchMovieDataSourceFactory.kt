package com.sample.android.moviemvvm.paging.search.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        private val api: MovieApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<Movie>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie> =
            PageKeyedSearchMovieDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}