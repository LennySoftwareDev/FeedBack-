package com.appdev.feedback.ui.screens.splash.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.navigation.Screen
import com.appdev.feedback.ui.screens.splash.content.SplashContent
import com.appdev.feedback.ui.screens.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setDefaultCredentials()
        delay(2000)
        navController.navigate(Screen.LoginScreen.route) {
            launchSingleTop = true
        }
        navController.clearBackStack(Screen.SplashScreen.route)
    }
    SplashContent()
}