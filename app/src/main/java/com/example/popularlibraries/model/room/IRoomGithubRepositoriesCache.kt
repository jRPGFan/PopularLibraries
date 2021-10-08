package com.example.popularlibraries.model.room

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubUserRepository>>
    fun cacheRoomRepos(user: GithubUser, githubRepos: List<GithubUserRepository>): Completable
}