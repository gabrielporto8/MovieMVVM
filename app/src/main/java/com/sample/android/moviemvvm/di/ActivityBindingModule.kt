package com.sample.android.moviemvvm.di

import com.sample.android.moviemvvm.ui.MainActivity
import com.sample.android.moviemvvm.ui.item.ItemModule
import com.sample.android.moviemvvm.ui.detail.DetailActivity
import com.sample.android.moviemvvm.ui.detail.DetailModule
import com.sample.android.moviemvvm.ui.person.PersonActivity
import com.sample.android.moviemvvm.ui.person.PersonModule
import com.sample.android.moviemvvm.ui.search.SearchActivity
import com.sample.android.moviemvvm.ui.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ItemModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun detailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun personActivity(): PersonActivity
}