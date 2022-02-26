package com.iions.done

import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplicationImpl : MainApplication() {

    override fun initConfig() {
        initializeStetho()
    }

    private fun initializeStetho() {
        val builder = Stetho.newInitializerBuilder(this)
        builder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        builder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        Stetho.initialize(builder.build())
    }

}