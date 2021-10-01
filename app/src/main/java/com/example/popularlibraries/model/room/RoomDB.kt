package com.example.popularlibraries.model.room

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: RoomDB? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created.Please call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, RoomDB::class.java, DB_NAME).build()
            }
        }
    }
}