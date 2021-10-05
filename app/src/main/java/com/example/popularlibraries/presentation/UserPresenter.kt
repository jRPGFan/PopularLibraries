package com.example.popularlibraries.presentation

import android.util.Log
import com.example.popularlibraries.model.*
import com.example.popularlibraries.screens.IScreens
import com.example.popularlibraries.view.UserReposView
import com.example.popularlibraries.view.ui.UserInfoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

class UserPresenter(
    private val user: GithubUser?
) : MvpPresenter<UserInfoView>() {
    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class RepoListPresenter : IUserRepoListPresenter {
        val repos = mutableListOf<GithubUserRepository>()
        override var itemClickListener: ((UserReposView) -> Unit)? = null
        override fun getCount() = repos.size
        override fun bindView(view: UserReposView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setRepo(it) }
        }
    }

    val reposListPresent = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.let { it.login?.let { it1 -> viewState.setUsername(it1) } }
        viewState.init()
        user?.let { loadRepos(it) }

        reposListPresent.itemClickListener = { userReposView ->
            router.navigateTo(screens.showRepo(reposListPresent.repos[userReposView.pos]))
        }
    }

    private fun loadRepos(user: GithubUser) {
        repositoriesRepo.getRepositories(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                reposListPresent.repos.clear()
                reposListPresent.repos.addAll(repos)
                viewState.update()
            }, { throwable ->
                Timber.log(Log.ERROR, throwable)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}