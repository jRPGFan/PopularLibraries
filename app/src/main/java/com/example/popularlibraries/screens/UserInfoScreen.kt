package com.example.popularlibraries.screens

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.ui.UserInfoFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserInfoScreen(private val user: GithubUser) {
    fun createFragment(): Screen =  FragmentScreen { UserInfoFragment.newInstance(user) }
}