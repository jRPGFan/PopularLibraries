package com.example.popularlibraries.modules

import com.example.popularlibraries.model.GithubRepositoriesRepo
import com.example.popularlibraries.model.IApiHolder
import com.example.popularlibraries.model.IGithubRepositoriesRepo
import com.example.popularlibraries.model.room.IRoomGithubRepositoriesCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun repositoriesRepo(
        apiHolder: IApiHolder,
        networkStatus: INetworkStatus,
        db: RoomDB,
        cache: IRoomGithubRepositoriesCache
    ): IGithubRepositoriesRepo = GithubRepositoriesRepo(apiHolder, networkStatus, db, cache)
}