package com.sample.android.moviemvvm.network

import com.sample.android.moviemvvm.domain.CreditWrapper
import com.sample.android.moviemvvm.domain.ItemWrapper
import com.sample.android.moviemvvm.domain.Movie
import com.sample.android.moviemvvm.domain.VideoWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun topRatedItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/movie/upcoming?language=en")
    fun latestItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/search/movie?language=en")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Call<ItemWrapper<Movie>>

    @GET("3/movie/{movieId}/videos")
    fun movieTrailers(@Path("movieId") movieId: Int): Observable<VideoWrapper>

    @GET("3/movie/{movieId}/credits")
    fun movieCredit(@Path("movieId") movieId: Int): Observable<CreditWrapper>
}