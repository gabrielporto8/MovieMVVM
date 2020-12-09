package com.sample.android.moviemvvm.ui.item.movie

import com.sample.android.moviemvvm.util.SortType.UPCOMING
import javax.inject.Inject

class UpcomingMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = UPCOMING
}