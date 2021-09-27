package com.example.popularlibraries.presentation

import android.util.Log
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import com.example.popularlibraries.model.IGithubUsersRepo
import com.example.popularlibraries.screens.IScreens
import com.example.popularlibraries.view.UserReposView
import com.example.popularlibraries.view.ui.UserInfoView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import timber.log.Timber

class UserPresenter(
    private val user: GithubUser?,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UserInfoView>() {
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
        loadRepos()

        reposListPresent.itemClickListener = { userReposView ->
            router.navigateTo(screens.showRepo(reposListPresent.repos[userReposView.pos]))
        }
    }

    private fun loadRepos() {
        user?.login?.let { usersRepo.getUserRepos(it).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                reposListPresent.repos.clear()
                reposListPresent.repos.addAll(repos)
                viewState.update()
            }, { throwable ->
                Timber.log(Log.ERROR, throwable)
            })
            }
//        usersRepo.getUserRepos().observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ repos ->
//                reposListPresent.repos.clear()
//                reposListPresent.repos.addAll(repos)
//                viewState.update()
//            }, { throwable ->
//                Timber.log(Log.ERROR, throwable)
//            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}