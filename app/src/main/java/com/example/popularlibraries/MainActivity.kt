package com.example.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.interfaces.MainView
import com.example.popularlibraries.presenters.MainPresenter

const val IS_COUNTERS = "counters"

class MainActivity : AppCompatActivity(), MainView {

    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.counterButton1.setOnClickListener { presenter.counterOneClick() }
        viewBinding.counterButton2.setOnClickListener { presenter.counterTwoClick() }
        viewBinding.counterButton3.setOnClickListener { presenter.counterThreeClick() }
        presenter.initCounters()
    }

    override fun setCounterOneText(text: String) {
        viewBinding.counterButton1.text = text
    }

    override fun setCounterTwoText(text: String) {
        viewBinding.counterButton2.text = text
    }

    override fun setCounterThreeText(text: String) {
        viewBinding.counterButton3.text = text
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(IS_COUNTERS, presenter.getCounters().toIntArray())
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putIntArray(IS_COUNTERS, presenter.getCounters().toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray(IS_COUNTERS)
        countersArray?.let {
            presenter.setCounters(countersArray)
        }
        presenter.initCounters()
    }
}