package com.example.popularlibraries.view

interface IImageLoader<T> {
    fun loadImageTo(url: String, container: T)
}