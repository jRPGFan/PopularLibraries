package com.example.popularlibraries.presentation

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.ui.UserInfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val user: GithubUser?, private val router: Router) :
    MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        user?.let { viewState.setUsername(it.login) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}