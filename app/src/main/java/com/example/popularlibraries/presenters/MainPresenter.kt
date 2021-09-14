package com.example.popularlibraries.presenters

import com.example.popularlibraries.interfaces.MainView
import com.example.popularlibraries.models.CountersModel

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterOneClick() {
        val nextValue = model.next(0)
        view.setCounterOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        view.setCounterTwoText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        view.setCounterThreeText(nextValue.toString())
    }

    fun initCounters() {
        view.setCounterOneText(model.getCurrent(0).toString())
        view.setCounterTwoText(model.getCurrent(1).toString())
        view.setCounterThreeText(model.getCurrent(2).toString())
    }

    fun getCounters(): MutableList<Int> {
        return model.getCounters()
    }

    fun setCounters(savedCounters: IntArray) {
        model.setCounters(savedCounters)
    }
}