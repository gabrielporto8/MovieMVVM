package com.sample.android.moviemvvm.paging.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyRepository
import com.sample.android.moviemvvm.util.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val api: MovieApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<Movie> =
            MoviesDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}