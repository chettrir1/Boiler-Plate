package com.iions.done.di.module

import android.app.Application
import android.content.Context
import com.iions.DatabaseManager
import com.iions.SharedPreferenceManager
import com.iions.done.utils.SchedulersFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        (AuthModule::class),
        (MainModule::class)
    ]
)
open class ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideScheduler() = object : SchedulersFactory {
        override fun ui(): MainCoroutineDispatcher = Dispatchers.Main
        override fun io(): CoroutineDispatcher = Dispatchers.IO
    }

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferenceManager {
        return SharedPreferenceManager(context)
    }

    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return DatabaseManager(context)
    }
}