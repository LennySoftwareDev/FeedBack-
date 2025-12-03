package com.appdev.feedback.ui.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object LoginScreen : Screen("login_screen")
    data object SocialMediaPostsScreen : Screen("social_media_posts_screen")
    data object PostCommentsScreen : Screen("post_comments_screen")
}