package com.sample.android.moviemvvm.ui.detail.tvshow

import com.sample.android.moviemvvm.domain.CreditWrapper
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.domain.VideoWrapper
import com.sample.android.moviemvvm.network.TVShowApi
import com.sample.android.moviemvvm.ui.detail.DetailViewModel
import io.reactivex.Observable

class TVShowDetailViewModel(private val api: TVShowApi,
                            private val item: TmdbItem) : DetailViewModel() {

    override fun getTrailers(): Observable<VideoWrapper> = api.tvTrailers(item.id)

    override fun getCredit(): Observable<CreditWrapper> = api.tvCredit(item.id)
}