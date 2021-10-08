package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(private val database: RoomDB) : IRoomGithubRepositoriesCache {
    override fun getUserRepos(user: GithubUser): Single<List<GithubUserRepository>> =
        Single.fromCallable {
            val roomUser = database.userDao.findByLogin(user.login.orEmpty() ?: error(""))
            return@fromCallable database.repositoryDao.findForUser(roomUser?.id.orEmpty()).map {
                GithubUserRepository(
                    id = it.id,
                    name = it.name,
                    forks = it.forksCount
                )
            }
        }

    override fun cacheRoomRepos(
        user: GithubUser,
        githubRepos: List<GithubUserRepository>
    ): Completable =
        Completable.fromAction {
            val roomUser = database.userDao.findByLogin(user.login.orEmpty()) ?: error("")
            val roomRepos = githubRepos.map {
                RoomGithubRepository(
                    id = it.id ?: "",
                    name = it.name ?: "",
                    forksCount = it.forks ?: -1,
                    userId = roomUser.id
                )
            }
            database.repositoryDao.insert(roomRepos)
        }
}