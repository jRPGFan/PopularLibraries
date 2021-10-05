package com.example.popularlibraries.model

import com.example.popularlibraries.model.room.IRoomGithubRepositoriesCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubRepositoriesRepo(
    private val api: IApiHolder, private val networkStatus: INetworkStatus, private val db: RoomDB,
    private val reposCache: IRoomGithubRepositoriesCache
): IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GithubUserRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                val repos = api.apiService.loadUserRepos(user.reposUrl.orEmpty())
                reposCache.cacheRoomRepos(repos, db, user)
                return@flatMap repos
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("User not found")
                    db.repositoryDao.findForUser(roomUser.id).map {
                        GithubUserRepository(it.id, it.name, it.forksCount)
                    }
                }
            }
        }
}