package com.example.popularlibraries.modules.components

import com.example.popularlibraries.MainActivity
import com.example.popularlibraries.modules.*
import com.example.popularlibraries.presenters.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CiceroneModule::class, DatabaseModule::class, ApiModule::class])
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
//    fun inject(usersPresenter: UsersPresenter)
//    fun inject(userPresenter: UserPresenter)
//    fun inject(repoPresenter: RepoPresenter)

//    fun inject(userFragment: UserInfoFragment)
//    fun inject(repositoryFragment: RepoFragment)
//    fun inject(usersFragment: UsersFragment)
}