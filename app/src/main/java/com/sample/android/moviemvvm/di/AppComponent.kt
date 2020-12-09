package com.sample.android.moviemvvm.di

import android.app.Application

import com.sample.android.moviemvvm.TmdbApp
import com.sample.android.moviemvvm.network.NetworkModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class])
interface AppComponent : AndroidInjector<TmdbApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
