package com.appdev.feedback.ui.screens.postcomments.page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.screens.postcomments.content.PostCommentsContent
import com.appdev.feedback.ui.screens.socialmediaposts.viewmodel.ViewModelSocialMediaPosts

@Composable
fun PostCommentsScreen(
    navController: NavController,

){
    PostCommentsContent(
        navController
    )
}