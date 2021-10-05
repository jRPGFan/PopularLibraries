package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRoomGithubUsersCache {
    fun cacheRoomUsers(githubUsers: Single<List<GithubUser>>)
}