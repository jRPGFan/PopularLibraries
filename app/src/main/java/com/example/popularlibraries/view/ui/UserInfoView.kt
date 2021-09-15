package com.example.popularlibraries.view.ui

import com.example.popularlibraries.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserInfoView : MvpView {
    fun setUsername(username: String)
}