package com.sample.android.moviemvvm.ui.item.tvshow

import com.sample.android.moviemvvm.util.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = HIGHEST_RATED
}