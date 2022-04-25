package com.iions.done.di.module

import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.feature.appointment.data.AppointmentRepository
import com.iions.done.feature.appointment.data.AppointmentRepositoryImpl
import com.iions.done.feature.appointment.data.local.AppointmentLocalImpl
import com.iions.done.feature.appointment.data.remote.AppointmentRemoteImpl
import com.iions.done.remote.ApiService
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class AppointmentModule {

    @Provides
    fun provideLocal(
        sharedPreferenceManager: SharedPreferenceManager,
        databaseManager: DatabaseManager
    ): AppointmentRepository.Local {
        return AppointmentLocalImpl(sharedPreferenceManager, databaseManager)
    }

    @Provides
    fun provideRemote(
        apiService: ApiService
    ): AppointmentRepository.Remote {
        return AppointmentRemoteImpl(apiService)
    }

    @Provides
    fun provideRepository(
        schedulersFactory: SchedulersFactory,
        localRepository: AppointmentRepository.Local,
        remoteRepository: AppointmentRepository.Remote
    ): AppointmentRepository {
        return AppointmentRepositoryImpl(schedulersFactory, localRepository, remoteRepository)
    }
}