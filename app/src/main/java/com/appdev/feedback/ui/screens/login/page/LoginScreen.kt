import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appdev.feedback.ui.navigation.Screen
import com.appdev.feedback.ui.screens.login.content.LoginContent
import com.appdev.feedback.ui.screens.login.event.UIEventLogin
import com.appdev.feedback.ui.screens.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    //val eventFlow = viewModel.eventFlow.collectAsState(initial = null)
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoginEnabled by viewModel.isLoginEnabled.collectAsState()
    val isLoginSuccess by viewModel.isLogiSuccess.collectAsState()


    LoginContent(
        username = username,
        onUsernameChange = viewModel::onUsernameChange,
        password = password,
        onPasswordChange = viewModel::onPasswordChange,
        isLoginEnabled = isLoginEnabled,
        onLoginClick = {
            viewModel.login()
        }
    )

    LaunchedEffect(true) {
        viewModel.eventFlow.collect { event ->

            when (event) {
                is UIEventLogin.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is UIEventLogin.Navigate -> {
                    if (event.screen == Screen.SocialMediaPostsScreen.route) {
                        navController.navigate(event.screen)
                        {
                            popUpTo(Screen.LoginScreen.route) { inclusive = true }
                            popUpTo(Screen.SplashScreen.route) { inclusive = true }
                        }
                    }

                }
            }
        }
    }
}
