package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoriesCache {
    fun cacheRoomRepos(githubRepos: Single<List<GithubUserRepository>>, db: RoomDB, user: GithubUser)
}