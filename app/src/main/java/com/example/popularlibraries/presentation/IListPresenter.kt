package com.example.popularlibraries.presentation

import com.example.popularlibraries.view.IItemView
import com.example.popularlibraries.view.UserItemView
import com.example.popularlibraries.view.UserReposView

interface IListPresenter<view : IItemView> {
    var itemClickListener: ((view) -> Unit)?
    fun bindView(view: view)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<UserItemView>

interface IUserRepoListPresenter: IListPresenter<UserReposView>