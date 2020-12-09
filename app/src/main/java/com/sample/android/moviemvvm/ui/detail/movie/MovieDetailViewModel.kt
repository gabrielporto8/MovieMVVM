package com.sample.android.moviemvvm.ui.detail.movie

import com.sample.android.moviemvvm.domain.CreditWrapper
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.domain.VideoWrapper
import com.sample.android.moviemvvm.network.MovieApi
import com.sample.android.moviemvvm.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(private val api: MovieApi,
                           private val item: TmdbItem) : DetailViewModel() {

    override fun getTrailers(): Observable<VideoWrapper> = api.movieTrailers(item.id)

    override fun getCredit(): Observable<CreditWrapper> = api.movieCredit(item.id)
}