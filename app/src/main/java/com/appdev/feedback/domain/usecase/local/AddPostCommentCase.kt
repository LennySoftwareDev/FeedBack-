package com.appdev.feedback.domain.usecase.local

import com.appdev.feedback.data.local.mapper.toPostCommentEntity
import com.appdev.feedback.data.local.models.PostCommentDto
import com.appdev.feedback.data.local.repository.PostCommentRepository
import javax.inject.Inject

class AddPostCommentCase @Inject constructor(
    private val repository: PostCommentRepository
){
    suspend operator fun invoke(postCommentMapper: PostCommentDto){
        repository.insertPostComment(postCommentMapper)
    }
}