package com.example.popularlibraries.utils

import com.example.popularlibraries.model.room.RoomDB
import java.io.File

interface IImageCache {
    fun imageCache(database: RoomDB, cacheDir: File)
}