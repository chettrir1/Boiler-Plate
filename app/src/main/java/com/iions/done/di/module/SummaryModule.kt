package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.feature.summary.data.SummaryRepositoryImpl
import com.iions.done.feature.summary.data.local.SummaryLocalImpl
import com.iions.done.feature.summary.data.remote.SummaryRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class SummaryModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): SummaryRepository.Local {
        return SummaryLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): SummaryRepository.Remote {
        return SummaryRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: SummaryRepository.Local,
        remoteRepository: SummaryRepository.Remote
    ): SummaryRepository {
        return SummaryRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}