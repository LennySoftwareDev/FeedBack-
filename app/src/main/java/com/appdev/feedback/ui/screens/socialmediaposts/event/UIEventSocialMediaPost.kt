package com.appdev.feedback.ui.screens.socialmediaposts.event

import com.appdev.feedback.ui.models.UserPost

sealed class UIEventSocialMediaPost {
    data class Error(val message: String) : UIEventSocialMediaPost()
    data class UserPostData(val userPost: List<UserPost>) : UIEventSocialMediaPost()
    object Loading : UIEventSocialMediaPost()
}