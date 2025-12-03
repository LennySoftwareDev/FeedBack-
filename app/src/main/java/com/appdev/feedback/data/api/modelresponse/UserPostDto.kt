package com.appdev.feedback.data.api.modelresponse

data class UserPostDto(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)