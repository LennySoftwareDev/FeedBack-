package com.appdev.feedback.data.local.repository

import com.appdev.feedback.data.local.database.dao.UserPostDao
import com.appdev.feedback.data.local.database.entities.PostCommentEntity
import com.appdev.feedback.data.local.mapper.toPostCommentEntity
import com.appdev.feedback.data.local.models.PostCommentDto
import com.appdev.feedback.ui.models.PostComment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCommentRepository @Inject constructor(
    private val userPostDao: UserPostDao
) {
    suspend fun insertPostComment(postCommentMapper : PostCommentDto){
        userPostDao.insertPostComment(postCommentMapper.toPostCommentEntity())
    }

    fun getPostComments(postId: Int) : Flow<List<PostCommentEntity>>{
        return userPostDao.getPostComments(postId)
    }
}