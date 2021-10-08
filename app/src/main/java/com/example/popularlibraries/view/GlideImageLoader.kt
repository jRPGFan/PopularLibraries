package com.example.popularlibraries.view

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadImageTo(url: String, container: ImageView) {
        Glide.with(container.context).load(url).into(container)
    }
}