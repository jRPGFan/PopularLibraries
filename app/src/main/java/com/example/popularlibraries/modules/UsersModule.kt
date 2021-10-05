package com.example.popularlibraries.modules

import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.model.IApiHolder
import com.example.popularlibraries.model.IGithubUsersRepo
import com.example.popularlibraries.model.room.IRoomGithubUsersCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsersModule {
    @Singleton
    @Provides
    fun usersRepo(
        api: IApiHolder,
        networkStatus: INetworkStatus,
        cache: IRoomGithubUsersCache,
        db: RoomDB
    ): IGithubUsersRepo = GithubUsersRepo(api, networkStatus, cache, db)
}