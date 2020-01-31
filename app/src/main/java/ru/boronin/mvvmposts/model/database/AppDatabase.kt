package ru.boronin.mvvmposts.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.boronin.mvvmposts.model.Post
import ru.boronin.mvvmposts.model.PostDao

/**
 * Created by Sergey Boronin on 31.01.2020.
 */

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}