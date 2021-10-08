package com.example.popularlibraries.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentRepoItemBinding
import com.example.popularlibraries.model.GithubUserRepository
import com.example.popularlibraries.presentation.RepoPresenter
import com.example.popularlibraries.view.UserRepoView
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

const val USER_REPO = "user_repo"

class RepoFragment : MvpAppCompatFragment(), UserRepoView, BackButtonListener {
    @Inject
    lateinit var router: Router
    private var _viewBinding: FragmentRepoItemBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val repo: GithubUserRepository? by lazy { arguments?.getParcelable(USER_REPO) }
    private val presenter by moxyPresenter {
        RepoPresenter(repo).apply { App.instance.repositorySubcomponent?.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentRepoItemBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun setForks(forks: Int) {
        viewBinding.repoForks.text = String.format("Fork amount: %d", forks)
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance(repo: GithubUserRepository): Fragment =
            RepoFragment().apply {
                arguments = bundleOf(USER_REPO to repo)
//                App.instance.appComponent.inject(this)
            }
    }
}