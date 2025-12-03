package com.appdev.feedback.data.local.models

data class PostCommentDto (
    val commentId: Int,
    val postId: Int,
    val body: String
)