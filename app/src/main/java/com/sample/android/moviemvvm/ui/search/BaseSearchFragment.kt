package com.sample.android.moviemvvm.ui.search

import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.ui.BaseFragment
import com.sample.android.moviemvvm.ui.TmdbAdapter
import com.sample.android.moviemvvm.util.NavType
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    override val navType: NavType by lazy { (activity as SearchActivity).navType }

    fun search(query: String) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (list.adapter as TmdbAdapter<T>).submitList(null)
        }
    }
}