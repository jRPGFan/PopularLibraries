package com.example.popularlibraries.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserRepoView : MvpView {
    fun setForks(forks: Int)
}