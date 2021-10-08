package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val database: RoomDB) : IRoomGithubUsersCache {

    override fun getRoomUsers(): Single<List<GithubUser>> = Single.fromCallable {
        database.userDao.getAll().map {
            GithubUser(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                reposUrl = it.reposUrl
            )
        }
    }

    override fun cacheRoomUsers(githubUsers: List<GithubUser>): Completable =
        Completable.fromAction {
            val roomUsers = githubUsers.map {
                RoomGithubUser(
                    id = it.id ?: "",
                    login = it.login ?: "",
                    avatarUrl = it.avatarUrl ?: "",
                    reposUrl = it.reposUrl ?: ""
                )
            }
            database.userDao.insert(roomUsers)
        }
}
