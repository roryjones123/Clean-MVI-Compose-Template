package com.rozworks.features.presentation.di

import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.HomepageNavigationFactory
import com.rozworks.features.presentation.HomepageUiState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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

@Module
@InstallIn(ViewModelComponent::class)
internal object HomepageViewModel {

    @Provides
    fun provideInitialCoinsUiState(): HomepageUiState = HomepageUiState()
}