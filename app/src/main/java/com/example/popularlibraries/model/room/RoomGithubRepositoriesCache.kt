package com.example.popularlibraries.model.room

import android.util.Log
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

class RoomGithubRepositoriesCache : IRoomGithubRepositoriesCache {
    override fun cacheRoomRepos(
        githubRepos: Single<List<GithubUserRepository>>,
        db: RoomDB,
        user: GithubUser
    ) {
        githubRepos.flatMap { repositories ->
            Single.fromCallable {
                val roomUser = db.userDao.findByLogin(user.login.orEmpty())
                    ?: throw RuntimeException("User not found")
                val roomRepos = repositories.map {
                    RoomGithubRepository(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        forksCount = it.forks ?: 0,
                        userId = roomUser.id
                    )
                }
                db.repositoryDao.insert(roomRepos)
                repositories.size.toString()
            }
        }.map { it }.subscribe { s ->
            Timber.log(Log.INFO, "Loaded %s repositories", s)
        }
    }
}