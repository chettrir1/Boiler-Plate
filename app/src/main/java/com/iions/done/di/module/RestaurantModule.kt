package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.groceries.data.GroceryRepository
import com.iions.done.feature.groceries.data.GroceryRepositoryImpl
import com.iions.done.feature.groceries.data.local.GroceryLocalImpl
import com.iions.done.feature.groceries.data.remote.GroceryRemoteImpl
import com.iions.done.feature.restaurants.data.RestaurantRepository
import com.iions.done.feature.restaurants.data.RestaurantRepositoryImpl
import com.iions.done.feature.restaurants.data.local.RestaurantLocalImpl
import com.iions.done.feature.restaurants.data.remote.RestaurantRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class RestaurantModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): RestaurantRepository.Local {
        return RestaurantLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): RestaurantRepository.Remote {
        return RestaurantRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: RestaurantRepository.Local,
        remoteRepository: RestaurantRepository.Remote
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}