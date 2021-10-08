package com.example.popularlibraries.model

import com.example.popularlibraries.model.room.IRoomGithubUsersCache
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    private val api: IApiHolder,
    private val networkStatus: INetworkStatus,
    private val usersCache: IRoomGithubUsersCache
) {
    fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.apiService.getUsers().flatMap { users ->
                    usersCache.cacheRoomUsers(users).toSingleDefault(users)
                }
            } else {
                usersCache.getRoomUsers()
            }
        }
}