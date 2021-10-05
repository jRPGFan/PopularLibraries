package com.example.popularlibraries.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentUserInfoBinding
import com.example.popularlibraries.model.ApiHolder
import com.example.popularlibraries.model.GithubRepositoriesRepo
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.model.room.RoomGithubRepositoriesCache
import com.example.popularlibraries.presentation.UserPresenter
import com.example.popularlibraries.screens.AndroidScreens
import com.example.popularlibraries.utils.AndroidNetworkStatus
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

const val USER_INFO = "user_info"

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    private var _viewBinding: FragmentUserInfoBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val user: GithubUser? by lazy { arguments?.getParcelable(USER_INFO) }
    private val presenter by moxyPresenter {
        UserPresenter(user).apply { App.instance.appComponent.inject(this) }
    }
    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun setUsername(username: String) {
        viewBinding.userID.text = username
    }

    override fun init() {
        viewBinding.userRepos.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReposRVAdapter(presenter.reposListPresent)
        viewBinding.userRepos.adapter = adapter
    }

    override fun update() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance(user: GithubUser): Fragment =
            UserInfoFragment().apply {
                arguments = bundleOf(USER_INFO to user)
//                App.instance.appComponent.inject(this)
            }
    }
}