package com.example.popularlibraries.modules.components

import com.example.popularlibraries.modules.UsersModule
import com.example.popularlibraries.modules.scopes.UserScope
import com.example.popularlibraries.presentation.UsersPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UsersModule::class])
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
}