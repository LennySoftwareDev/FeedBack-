package com.appdev.feedback.ui.models

data class PostComment (
    val id: Int,
    val postId: Int,
    val body: String
)