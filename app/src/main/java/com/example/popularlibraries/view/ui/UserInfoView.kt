package com.example.popularlibraries.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserInfoView : MvpView {
    fun setUsername(username: String)
}