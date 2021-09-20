package com.example.popularlibraries.screens

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.ui.UserInfoFragment
import com.example.popularlibraries.view.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun showUser(user: GithubUser) = FragmentScreen { UserInfoFragment.newInstance(user) }
}