package com.sample.android.moviemvvm.ui.item

import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.ui.BaseFragment
import com.sample.android.moviemvvm.ui.MainActivity
import com.sample.android.moviemvvm.util.NavType
import com.sample.android.moviemvvm.util.SortType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override val navType: NavType? by lazy { (activity as MainActivity).getNavType() }
}