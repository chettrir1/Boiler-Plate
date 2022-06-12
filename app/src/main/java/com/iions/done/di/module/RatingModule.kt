package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.rating.data.RatingRepository
import com.iions.done.feature.rating.data.RatingRepositoryImpl
import com.iions.done.feature.rating.data.local.RatingLocalImpl
import com.iions.done.feature.rating.data.remote.RatingRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class RatingModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): RatingRepository.Local {
        return RatingLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apolloClient: ApiService
    ): RatingRepository.Remote {
        return RatingRemoteImpl(apolloClient)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: RatingRepository.Local,
        remoteRepository: RatingRepository.Remote
    ): RatingRepository {
        return RatingRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}