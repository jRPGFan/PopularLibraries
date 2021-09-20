package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single

object GithubUsersRepo : IGithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    override fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)
}