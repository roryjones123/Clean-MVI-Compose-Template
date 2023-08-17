package com.rozworks.features.presentation.di

import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.HomepageNavigationFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HomepageSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindHomepageNavigationFactory(factory: HomepageNavigationFactory): NavigationFactory
}
