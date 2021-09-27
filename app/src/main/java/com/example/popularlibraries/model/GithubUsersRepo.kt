package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())

        override fun getUserRepos(username: String): Single<List<GithubUserRepository>> =
        api.loadUserRepos(username).subscribeOn(Schedulers.io())
//    override fun getUserRepos(): Single<List<GithubUserRepository>> =
//        api.loadUserRepos().subscribeOn(Schedulers.io())
}
