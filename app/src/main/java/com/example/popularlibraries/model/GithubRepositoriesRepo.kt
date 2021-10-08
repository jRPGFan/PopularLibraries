package com.example.popularlibraries.model

import com.example.popularlibraries.model.room.IRoomGithubRepositoriesCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubRepositoriesRepo(
    private val api: IApiHolder,
    private val networkStatus: INetworkStatus,
    private val reposCache: IRoomGithubRepositoriesCache
) {
    fun getRepositories(user: GithubUser): Single<List<GithubUserRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.apiService.loadUserRepos(user.reposUrl.orEmpty()).flatMap { repositories ->
                    reposCache.cacheRoomRepos(user, repositories).toSingleDefault(repositories)
                }
            } else
                reposCache.getUserRepos(user)
        }
}
