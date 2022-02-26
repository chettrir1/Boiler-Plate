package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.splash.data.SplashRepository
import com.iions.done.feature.splash.data.SplashRepositoryImpl
import com.iions.done.feature.splash.data.local.SplashLocalImpl
import com.iions.done.feature.splash.data.remote.SplashRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class SplashModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): SplashRepository.Local {
        return SplashLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): SplashRepository.Remote {
        return SplashRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: SplashRepository.Local,
        remoteRepository: SplashRepository.Remote
    ): SplashRepository {
        return SplashRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}