package ru.boronin.mvvmposts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sergey Boronin on 31.01.2020.
 */
@Entity
data class Post(
    val userId: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)