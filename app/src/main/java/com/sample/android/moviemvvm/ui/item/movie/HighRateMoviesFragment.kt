package com.sample.android.moviemvvm.ui.item.movie

import com.sample.android.moviemvvm.util.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = HIGHEST_RATED
}