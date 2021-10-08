package com.example.popularlibraries.modules

import com.example.popularlibraries.App
import com.example.popularlibraries.model.room.RoomDB
import com.example.popularlibraries.model.room.RoomImageCache
import com.example.popularlibraries.utils.IImageCache
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {
    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageCache(database: RoomDB, @Named("cacheDir") cacheDir: File): IImageCache =
        RoomImageCache(database, cacheDir)
}