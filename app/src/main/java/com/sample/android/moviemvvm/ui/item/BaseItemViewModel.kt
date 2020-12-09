package com.sample.android.moviemvvm.ui.item

import android.app.Application
import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.ui.TmdbViewModel

abstract class BaseItemViewModel<T : TmdbItem>(app: Application) : TmdbViewModel<T>(app = app) {

    init {
        showQuery("")
    }
}