package com.sample.android.moviemvvm.ui.detail

import com.sample.android.moviemvvm.domain.TmdbItem
import com.sample.android.moviemvvm.ui.detail.movie.MovieDetailFragment
import com.sample.android.moviemvvm.ui.detail.tvshow.TVShowDetailFragment
import com.sample.android.moviemvvm.util.NavType
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @ContributesAndroidInjector
    internal abstract fun movieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    internal abstract fun tvShowDetailFragment(): TVShowDetailFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideTmdbItem(activity: DetailActivity): TmdbItem =
                activity.intent.extras.getParcelable(EXTRA_TMDB_ITEM)

        @Provides
        @JvmStatic
        internal fun provideNavType(activity: DetailActivity): NavType =
                activity.intent.extras.getParcelable(EXTRA_NAV_TYPE)
    }
}
