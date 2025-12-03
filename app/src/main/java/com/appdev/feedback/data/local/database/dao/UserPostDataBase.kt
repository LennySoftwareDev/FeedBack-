package com.appdev.feedback.data.local.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appdev.feedback.data.local.database.entities.PostCommentEntity
import com.appdev.feedback.data.local.database.entities.UserPostEntity

@Database(entities = [UserPostEntity::class, PostCommentEntity::class], version = 1)
abstract class UserPostDataBase : RoomDatabase() {
    abstract fun userPostDao(): UserPostDao
}