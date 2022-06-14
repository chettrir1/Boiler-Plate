package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.search.data.SearchRepository
import com.iions.done.feature.search.data.SearchRepositoryImpl
import com.iions.done.feature.search.data.local.SearchLocalImpl
import com.iions.done.feature.search.data.remote.SearchRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class SearchModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): SearchRepository.Local {
        return SearchLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): SearchRepository.Remote {
        return SearchRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: SearchRepository.Local,
        remoteRepository: SearchRepository.Remote
    ): SearchRepository {
        return SearchRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}