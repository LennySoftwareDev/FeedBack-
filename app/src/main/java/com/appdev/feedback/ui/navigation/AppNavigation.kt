package com.appdev.feedback.ui.navigation

import LoginScreen
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appdev.feedback.ui.navigation.NavigationRoutes.POST_COMMENTS_SCREEN
import com.appdev.feedback.ui.screens.postcomments.page.PostCommentsScreen
import com.appdev.feedback.ui.screens.socialmediaposts.page.SocialMediaPostsScreen
import com.appdev.feedback.ui.screens.splash.page.SplashScreen

@Composable
fun AppNavigation() {
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

        composable(
            route = POST_COMMENTS_SCREEN,
            arguments = listOf(navArgument("userId") { type = NavType.IntType }
            )
        ){ navBackStackEntry ->
            val postId = navBackStackEntry.arguments?.getInt("userId") ?: 0
            PostCommentsScreen(
                navController = navController,
                postId = postId.toString()
            )
        }
    }
}