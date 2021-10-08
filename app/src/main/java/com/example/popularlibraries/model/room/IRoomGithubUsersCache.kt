package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubUsersCache {
    fun getRoomUsers(): Single<List<GithubUser>>
    fun cacheRoomUsers(githubUsers: List<GithubUser>): Completable
}