package com.sample.android.moviemvvm.ui.item.tvshow

import com.sample.android.moviemvvm.util.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = MOST_POPULAR
}