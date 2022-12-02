package com.iions

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseManager @Inject constructor(context: Context) {

    private val instance = MainDatabase.getInstance(context)

    fun getInstance(): MainDatabase = instance
    fun getUserDao() = instance.getUsersDao()
}