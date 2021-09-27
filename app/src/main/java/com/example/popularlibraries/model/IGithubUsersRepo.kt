package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {

	fun getUsers(): Single<List<GithubUser>>

	fun getUserRepos(username: String): Single<List<GithubUserRepository>>
//	fun getUserRepos(): Single<List<GithubUserRepository>>
}