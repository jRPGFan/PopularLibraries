package com.example.popularlibraries

import android.os.Bundle
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.presenters.MainPresenter
import com.example.popularlibraries.screens.AndroidScreens
import com.example.popularlibraries.view.ui.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
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