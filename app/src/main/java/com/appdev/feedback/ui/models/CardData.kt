package com.appdev.feedback.ui.models

sealed class CardData {
    data class UserPostItem(val value: UserPost) : CardData()
    data class PostCommentItem(val value: PostComment) : CardData()
}
