package com.sample.android.moviemvvm

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.moviemvvm.domain.ItemWrapper
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.paging.movie.MoviePageKeyRepository
import com.sample.android.moviemvvm.util.SortType
import com.sample.android.moviemvvm.util.isNetworkAvailable
import io.mockk.every
import io.mockk.mockkStatic
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.mock.Calls
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class MoviePageKeyRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieApi

    @Mock
    private lateinit var context: Context

    private val networkExecutor = Executor { command -> command.run() }

    @Test
    fun loadMostPopularMovies() {
        mockkStatic("com.sample.android.moviemvvm.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        val repository = MoviePageKeyRepository(api, SortType.MOST_POPULAR, context)
        val movie = Movie(1, "overview", "date",
                null, null, "title", 6.5)

        val mockCall = Calls.response(Response.success(
                ItemWrapper(Lists.newArrayList(movie))))
        `when`(api.popularItems(anyInt())).thenReturn(mockCall)

        val listing = repository.getItems(networkExecutor)
        val observer = LoggingObserver<PagedList<Movie>>()
        listing.pagedList.observeForever(observer)

        with(observer.value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    /**
     * simple observer that logs the latest value it receives
     */
    private class LoggingObserver<T> : Observer<T> {
        var value: T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}