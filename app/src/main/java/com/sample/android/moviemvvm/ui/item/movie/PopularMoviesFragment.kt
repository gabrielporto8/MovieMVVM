package com.sample.android.moviemvvm.ui.item.movie

import com.sample.android.moviemvvm.util.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = MOST_POPULAR
}