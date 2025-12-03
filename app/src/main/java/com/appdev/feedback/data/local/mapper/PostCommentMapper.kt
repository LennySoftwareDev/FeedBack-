package com.appdev.feedback.data.local.mapper

import com.appdev.feedback.data.local.database.entities.PostCommentEntity
import com.appdev.feedback.data.local.models.PostCommentDto
import com.appdev.feedback.ui.models.PostComment

fun PostCommentDto.toPostCommentEntity(): PostCommentEntity{
    return PostCommentEntity(
        postId = this.postId,
        body = this.body
    )
}

fun PostCommentEntity.toPostComment(): PostComment = PostComment(
    commentId = this.commentId,
    postId = this.postId,
    body = this.body
)