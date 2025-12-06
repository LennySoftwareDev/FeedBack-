package com.appdev.feedback.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comment",
    foreignKeys = [
        ForeignKey(
            entity = UserPostEntity::class,
            parentColumns = ["id"],
            childColumns = ["postId"],
        )
    ],
    indices = [Index("postId")]
)
data class PostCommentEntity(
    @PrimaryKey(autoGenerate = true)
    val commentId: Int = 0,
    val postId: Int,
    val body: String,
)
