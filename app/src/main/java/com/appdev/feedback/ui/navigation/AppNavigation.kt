package com.appdev.feedback.ui.navigation

import LoginScreen
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appdev.feedback.ui.screens.postcomments.page.PostCommentsScreen
import com.appdev.feedback.ui.screens.socialmediaposts.page.SocialMediaPostsScreen
import com.appdev.feedback.ui.screens.splash.page.SplashScreen

@Composable
fun AppNavigation(activity: ComponentActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.SocialMediaPostsScreen.route) {
            SocialMediaPostsScreen(navController)
        }
        composable(route = Screen.PostCommentsScreen.route) {
            PostCommentsScreen(navController)
        }
    }
}