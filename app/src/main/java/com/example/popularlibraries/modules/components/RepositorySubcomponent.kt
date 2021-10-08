package com.example.popularlibraries.modules.components

import com.example.popularlibraries.modules.RepositoryModule
import com.example.popularlibraries.modules.scopes.RepositoryScope
import com.example.popularlibraries.presentation.RepoPresenter
import com.example.popularlibraries.presentation.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepoPresenter)
}