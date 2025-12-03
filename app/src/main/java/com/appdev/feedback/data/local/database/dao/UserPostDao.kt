package com.appdev.feedback.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appdev.feedback.data.local.database.entities.PostCommentEntity
import com.appdev.feedback.data.local.database.entities.UserPostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPost(userPost: UserPostEntity)

    @Query("SELECT * FROM user_post")
    fun getUserPosts(): Flow<List<UserPostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostComment(postComment: PostCommentEntity)

    @Query("SELECT * FROM comment WHERE postId = :postId")
    fun getPostComments(postId: Int): Flow<List<PostCommentEntity>>
}