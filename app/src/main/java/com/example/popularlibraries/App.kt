package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.modules.AppComponent
import com.example.popularlibraries.modules.AppModule
import com.example.popularlibraries.modules.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import timber.log.Timber

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: App
    }
}