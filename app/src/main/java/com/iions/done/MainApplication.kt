package com.iions.done

import android.app.Application

abstract class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initConfig()
    }

    abstract fun initConfig()

}