package com.sample.android.moviemvvm.ui.search

import com.sample.android.moviemvvm.util.NavType
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchModule {

    @ContributesAndroidInjector
    internal abstract fun searchMovieFragment(): SearchMovieFragment

    @ContributesAndroidInjector
    internal abstract fun searchTVShowFragment(): SearchTVShowFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideNavType(activity: SearchActivity): NavType =
                activity.intent.extras.getParcelable(EXTRA_NAV_TYPE)
    }
}