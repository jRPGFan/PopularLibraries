package com.example.popularlibraries.modules

import com.example.popularlibraries.MainActivity
import com.example.popularlibraries.presentation.RepoPresenter
import com.example.popularlibraries.presentation.UserPresenter
import com.example.popularlibraries.presentation.UsersPresenter
import com.example.popularlibraries.presenters.MainPresenter
import com.example.popularlibraries.view.ui.RepoFragment
import com.example.popularlibraries.view.ui.UserInfoFragment
import com.example.popularlibraries.view.ui.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CiceroneModule::class, CacheModule::class,
    ApiModule::class, UsersModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)

//    fun inject(userFragment: UserInfoFragment)
//    fun inject(repositoryFragment: RepoFragment)
//    fun inject(usersFragment: UsersFragment)
}