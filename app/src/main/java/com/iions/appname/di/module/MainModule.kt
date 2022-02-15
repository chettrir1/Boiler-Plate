package com.iions.appname.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.appname.feature.main.data.MainRepository
import com.iions.appname.feature.main.data.MainRepositoryImpl
import com.iions.appname.feature.main.data.local.MainLocalImpl
import com.iions.appname.feature.main.data.remote.MainRemoteImpl
import com.iions.appname.remote.ApiService
import com.iions.appname.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class MainModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): MainRepository.Local {
        return MainLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): MainRepository.Remote {
        return MainRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: MainRepository.Local,
        remoteRepository: MainRepository.Remote
    ): MainRepository {
        return MainRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}