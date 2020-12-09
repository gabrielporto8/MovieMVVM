package com.sample.android.moviemvvm.ui.item.tvshow

import com.sample.android.moviemvvm.util.SortType.UPCOMING
import javax.inject.Inject

class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = UPCOMING
}