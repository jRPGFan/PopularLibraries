package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}/repos")
    fun loadUserRepos(@Path("login") login: String): Single<List<GithubUserRepository>>
//    @GET("/users/mojombo/repos")
//    fun loadUserRepos(): Single<List<GithubUserRepository>>

}