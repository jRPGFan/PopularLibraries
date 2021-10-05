package com.example.popularlibraries.presentation

import android.util.Log
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUserRepository
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.model.IGithubUsersRepo
import com.example.popularlibraries.screens.IScreens
import com.example.popularlibraries.view.UserItemView
import com.example.popularlibraries.view.ui.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

class UsersPresenter(private val uiScheduler: Scheduler) : MvpPresenter<UsersView>() {
    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.setAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.showUser(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        usersRepo.getUsers().observeOn(uiScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.update()
            }, {
                Timber.log(Log.ERROR, it.message)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}