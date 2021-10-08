package com.example.popularlibraries.model.room

import com.example.popularlibraries.utils.IImageCache
import java.io.File

class RoomImageCache(private val database: RoomDB, private val cacheDir: File) : IImageCache {
    override fun imageCache(database: RoomDB, cacheDir: File) {
        TODO("Not yet implemented")
    }
}