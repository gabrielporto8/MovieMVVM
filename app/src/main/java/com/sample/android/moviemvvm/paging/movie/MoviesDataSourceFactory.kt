package com.sample.android.moviemvvm.paging.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyedItemDataSource
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        private val api: MovieApi,
        private val sortType: SortType,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<Movie>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie> =
            PageKeyedMovieDataSource(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}
