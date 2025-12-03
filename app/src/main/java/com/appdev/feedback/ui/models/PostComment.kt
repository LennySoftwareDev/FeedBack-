package com.appdev.feedback.ui.models

data class PostComment (
    val commentId: Int,
    val postId: Int,
    val body: String
)