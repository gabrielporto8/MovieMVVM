package com.sample.android.moviemvvm.paging.search.movie

import android.content.Context
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.TmdbDataSourceFactory
import com.sample.android.moviemvvm.paging.PageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val api: MovieApi,
                                   private val query : String,
                                   private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<Movie> =
            SearchMovieDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}