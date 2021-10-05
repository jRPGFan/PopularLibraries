package com.example.popularlibraries.presentation

import com.example.popularlibraries.model.GithubUserRepository
import com.example.popularlibraries.view.UserRepoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter(private val repo: GithubUserRepository?) :
    MvpPresenter<UserRepoView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.let { it.forks?.let { it1 -> viewState.setForks(it1) } }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}