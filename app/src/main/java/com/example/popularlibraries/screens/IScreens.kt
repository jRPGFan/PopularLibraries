package com.example.popularlibraries.screens

import com.example.popularlibraries.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun showUser(user: GithubUser): Screen
}