package com.appdev.feedback.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_post"
)
data class UserPostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val title: String,
    val body: String,
)