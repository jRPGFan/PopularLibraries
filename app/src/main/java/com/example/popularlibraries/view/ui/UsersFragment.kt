package com.example.popularlibraries.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentUsersBinding
import com.example.popularlibraries.model.ApiHolder
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.presentation.UsersPresenter
import com.example.popularlibraries.screens.AndroidScreens
import com.example.popularlibraries.view.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _viewBinding: FragmentUsersBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            GithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init() {
        viewBinding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        viewBinding.rvUsers.adapter = adapter
    }

    override fun update() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance() = UsersFragment()
    }
}