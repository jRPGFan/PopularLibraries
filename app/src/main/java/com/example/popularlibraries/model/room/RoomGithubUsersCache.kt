package com.example.popularlibraries.model.room

import android.util.Log
import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

class RoomGithubUsersCache() : IRoomGithubUsersCache {

    override fun cacheRoomUsers(githubUsers: Single<List<GithubUser>>, db: RoomDB) {
        githubUsers.flatMap { users ->
            Single.fromCallable {
                val roomUsers = users.map { user ->
                    RoomGithubUser(
                        id = user.id ?: "",
                        login = user.login ?: "",
                        avatarUrl = user.avatarUrl ?: "",
                        reposUrl = user.reposUrl ?: ""
                    )
                }
                db.userDao.insert(roomUsers)
                return@fromCallable users.size.toString()
            }
        }.map { it }.subscribe { s ->
            Timber.log(Log.INFO, "Loaded %s users",s)
        }
    }
}
