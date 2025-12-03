package com.appdev.feedback.data.local.mapper

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.local.database.entities.UserPostEntity
import com.appdev.feedback.ui.models.UserPost

fun UserPostDto.toUserPostEntity(): UserPostEntity {
    return UserPostEntity(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}

fun UserPostEntity.toUserPost(): UserPost = UserPost(
    userId = this.userId,
    id = this.id,
    title = this.title,
    body = this.body
)