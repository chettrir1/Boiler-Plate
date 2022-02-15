package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.auth.data.AuthRepository
import com.iions.done.feature.auth.data.AuthRepositoryImpl
import com.iions.done.feature.auth.data.local.AuthLocalImpl
import com.iions.done.feature.auth.data.remote.AuthRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class AuthModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): AuthRepository.Local {
        return AuthLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apolloClient: ApiService
    ): AuthRepository.Remote {
        return AuthRemoteImpl(apolloClient)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: AuthRepository.Local,
        remoteRepository: AuthRepository.Remote
    ): AuthRepository {
        return AuthRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}