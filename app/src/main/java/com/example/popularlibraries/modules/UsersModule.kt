package com.example.popularlibraries.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.model.IApiHolder
import com.example.popularlibraries.model.IGithubUsersRepo
import com.example.popularlibraries.model.room.IRoomGithubUsersCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.model.room.RoomGithubUsersCache
import com.example.popularlibraries.modules.scopes.IUserScopeContainer
import com.example.popularlibraries.modules.scopes.UserScope
import com.example.popularlibraries.utils.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class UsersModule {
    @Provides
    fun usersCache(database: RoomDB): IRoomGithubUsersCache = RoomGithubUsersCache(database)

    @UserScope
    @Provides
    open fun usersRepo(
        api: IApiHolder,
        networkStatus: INetworkStatus,
        cache: IRoomGithubUsersCache
    ): GithubUsersRepo = GithubUsersRepo(api, networkStatus, cache)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}