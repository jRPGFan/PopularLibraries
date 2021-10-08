package com.example.popularlibraries.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.model.GithubRepositoriesRepo
import com.example.popularlibraries.model.IApiHolder
import com.example.popularlibraries.model.IGithubRepositoriesRepo
import com.example.popularlibraries.model.room.IRoomGithubRepositoriesCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.model.room.RoomGithubRepositoriesCache
import com.example.popularlibraries.modules.scopes.IRepositoryScopeContainer
import com.example.popularlibraries.modules.scopes.IUserScopeContainer
import com.example.popularlibraries.modules.scopes.RepositoryScope
import com.example.popularlibraries.utils.INetworkStatus
import dagger.Module
import dagger.Provides

//@Module
//class RepositoryModule {
//    @Singleton
//    @Provides
//    fun repositoriesRepo(
//        apiHolder: IApiHolder,
//        networkStatus: INetworkStatus,
//        db: RoomDB,
//        cache: IRoomGithubRepositoriesCache
//    ): IGithubRepositoriesRepo = GithubRepositoriesRepo(apiHolder, networkStatus, db, cache)
//}

@Module
open class RepositoryModule {
    @Provides
    fun repositoriesCache(database: RoomDB): IRoomGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IApiHolder,
        networkStatus: INetworkStatus,
        cache: IRoomGithubRepositoriesCache
    ): GithubRepositoriesRepo =
        GithubRepositoriesRepo(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}