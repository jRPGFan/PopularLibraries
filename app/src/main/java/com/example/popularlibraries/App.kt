package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.modules.components.AppComponent
import com.example.popularlibraries.modules.AppModule
import com.example.popularlibraries.modules.components.DaggerAppComponent
import com.example.popularlibraries.modules.components.RepositorySubcomponent
import com.example.popularlibraries.modules.components.UserSubcomponent
import com.example.popularlibraries.modules.scopes.IRepositoryScopeContainer
import com.example.popularlibraries.modules.scopes.IUserScopeContainer
import timber.log.Timber

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {
    lateinit var appComponent: AppComponent

    var userSubcomponent: UserSubcomponent? = null
        private set
    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also { userSubcomponent = it }
    fun initRepositorySubcomponent() =
        userSubcomponent?.repositorySubcomponent().also { repositorySubcomponent = it }

    override fun releaseUserScope() {
        userSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    companion object {
        lateinit var instance: App
    }
}