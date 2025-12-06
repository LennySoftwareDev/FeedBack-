package com.appdev.feedback.ui.screens.socialmediaposts.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.screens.socialmediaposts.content.SocialMediaPostsContent
import com.appdev.feedback.ui.screens.socialmediaposts.viewmodel.ViewModelSocialMediaPosts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaPostsScreen(
    navController: NavController,
    viewModel: ViewModelSocialMediaPosts = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Social Media Posts") }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SocialMediaPostsContent(navController)
        }
    }
}
