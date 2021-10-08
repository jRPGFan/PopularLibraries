package com.example.popularlibraries.modules

import com.example.popularlibraries.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}