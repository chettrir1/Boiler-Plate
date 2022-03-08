package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.GroceryRepositoryImpl
import com.iions.done.feature.groceries.data.local.GroceryLocalImpl
import com.iions.done.feature.groceries.data.remote.GroceryRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class GroceryModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): GroceryRepository.Local {
        return GroceryLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): GroceryRepository.Remote {
        return GroceryRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: GroceryRepository.Local,
        remoteRepository: GroceryRepository.Remote
    ): GroceryRepository {
        return GroceryRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}