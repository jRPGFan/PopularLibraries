package com.example.popularlibraries.model

import com.example.popularlibraries.model.room.IRoomGithubUsersCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IApiHolder,
    private val networkStatus: INetworkStatus,
    private val usersCache: IRoomGithubUsersCache,
    private val db: RoomDB
) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                val users = api.apiService.getUsers()
                usersCache.cacheRoomUsers(users)
                return@flatMap users
            } else {
                Single.fromCallable {
                    db.userDao.getAll().map { roomUser ->
                        GithubUser(
                            id = roomUser.id,
                            login = roomUser.login,
                            avatarUrl = roomUser.avatarUrl,
                            reposUrl = roomUser.reposUrl
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}