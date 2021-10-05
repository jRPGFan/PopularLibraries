package com.example.popularlibraries.screens

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun showUser(user: GithubUser): Screen
    fun showRepo(repo: GithubUserRepository): Screen
}