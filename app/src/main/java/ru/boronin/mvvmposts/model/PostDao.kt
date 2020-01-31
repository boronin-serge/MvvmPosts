package ru.boronin.mvvmposts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
@Dao
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg posts: Post)
}