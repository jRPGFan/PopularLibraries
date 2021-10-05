package com.example.popularlibraries.modules

import androidx.room.Room
import com.example.popularlibraries.App
import com.example.popularlibraries.model.room.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): RoomDB = Room.databaseBuilder(
        app,
        RoomDB::class.java, DB_NAME
    ).build()

    @Singleton
    @Provides
    fun usersCache(database: RoomDB): IRoomGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun reposCache() : IRoomGithubRepositoriesCache = RoomGithubRepositoriesCache()

    companion object {
        private const val DB_NAME = "database.db"
    }
}