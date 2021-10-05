package com.example.popularlibraries

import android.os.Bundle
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.presenters.MainPresenter
import com.example.popularlibraries.view.ui.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val presenter by moxyPresenter {
        MainPresenter().apply { App.instance.appComponent.inject(this) }
    }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}