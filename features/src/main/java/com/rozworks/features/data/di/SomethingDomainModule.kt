package com.rozworks.features.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.rozworks.features.data.remote.api.SomethingApi
import com.rozworks.features.data.repository.DefaultSomethingRepository
import com.rozworks.features.domain.repository.SomethingRepository
import com.rozworks.features.domain.usecase.GetHomepageDataUseCase
import com.rozworks.features.domain.usecase.getHomepageData
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SomethingDomainModule {

    @Provides
    @Singleton
    fun provideSomethingApi(
        retrofit: Retrofit,
    ): SomethingApi {
        return retrofit.create(SomethingApi::class.java)
    }
    @Provides
    fun provideGetHomepageData(
        somethingRepository: SomethingRepository
    ): GetHomepageDataUseCase {
        return GetHomepageDataUseCase {
            getHomepageData(somethingRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @Binds
        @Singleton
        fun bindSomethingRepository(defaultSomethingRepository: DefaultSomethingRepository): SomethingRepository

    }
}
