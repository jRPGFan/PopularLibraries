package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.model.room.RoomDB
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import timber.log.Timber

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        RoomDB.create(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: App
    }
}